package com.gaohanghang.springbootgephi.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gaohanghang.springbootgephi.dao.RelationShipRepository;
import com.gaohanghang.springbootgephi.entity.Relationship;
import com.gaohanghang.springbootgephi.jsonexporter.ExportJSON;
import com.gaohanghang.springbootgephi.jsonexporter.model.Color;
import com.gaohanghang.springbootgephi.jsonexporter.model.GraphEdge;
import com.gaohanghang.springbootgephi.jsonexporter.model.GraphElement;
import com.gaohanghang.springbootgephi.jsonexporter.model.GraphNode;
import com.google.gson.Gson;
import org.gephi.graph.api.*;
import org.gephi.io.database.drivers.PostgreSQLDriver;
import org.gephi.io.database.drivers.SQLUtils;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.importer.plugin.database.EdgeListDatabaseImpl;
import org.gephi.io.importer.plugin.database.ImporterEdgeList;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.preview.types.EdgeColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:37
 */
@Service
public class RelationshipService {

    Workspace workspace;
    @Autowired
    private RelationShipRepository relationShipRepository;

    public void excelImport(MultipartFile file) throws IOException {
        // sheet1
        List<Object> data = EasyExcelFactory.read(file.getInputStream(), new Sheet(1, 1, Relationship.class));
        print(data);

        for (Object item : data) {
            System.out.println(item);
            Relationship relationShip = (Relationship) item;
            relationShip.setId(UUID.randomUUID().toString());
            relationShipRepository.save(relationShip);
        }

        // sheet2
        List<Object> data2 = EasyExcelFactory.read(file.getInputStream(), new Sheet(2, 1, Relationship.class));
        print(data);

        for (Object item : data2) {
            System.out.println(item);
            Relationship relationShip = (Relationship) item;
            relationShip.setId(UUID.randomUUID().toString());
            relationShipRepository.save(relationShip);
        }

        // sheet3
        List<Object> data3 = EasyExcelFactory.read(file.getInputStream(), new Sheet(3, 1, Relationship.class));
        print(data);

        for (Object item : data3) {
            System.out.println(item);
            Relationship relationShip = (Relationship) item;
            relationShip.setId(UUID.randomUUID().toString());
            relationShipRepository.save(relationShip);
        }
    }

    public void print(List<Object> datas){
        int i=0;
        for (Object ob:datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }

    public String getJson() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        workspace = pc.getCurrentWorkspace();

        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

        //Import database
        EdgeListDatabaseImpl db = new EdgeListDatabaseImpl();
        db.setDBName("cobweb");
        db.setHost("10.0.0.156");
        db.setUsername("postgres");
        db.setPasswd("root");
        //db.setSQLDriver(new MySQLDriver());
        db.setSQLDriver(new PostgreSQLDriver());
        //db.setSQLDriver(new SQLServerDriver());
        db.setPort(5432);
        db.setNodeQuery("SELECT DISTINCT sender as id,sender as label FROM relationship UNION SELECT DISTINCT receiver as id,receiver as label FROM relationship;");
        db.setEdgeQuery("SELECT sender AS source, receiver AS target, concat(topic,' ',begin_time) AS label, weight FROM relationship");
        ImporterEdgeList edgeListImporter = new ImporterEdgeList();
        Container container = importController.importDatabase(db, edgeListImporter);
        container.getLoader().setAllowAutoNode(false);      //Don't create missing nodes
        container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force UNDIRECTED

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);

        //See if graph is well imported
        UndirectedGraph graph = graphModel.getUndirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());

        //Layout - 100 Yifan Hu passes
        YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(2f));
        layout.setGraphModel(graphModel);
        layout.resetPropertiesValues();
        layout.initAlgo();
        for (int i = 0; i < 100 && layout.canAlgo(); i++) {
            layout.goAlgo();
        }
        layout.endAlgo();

        //Export X, Y position to the DB
        //Connect database
        String url = SQLUtils.getUrl(db.getSQLDriver(), db.getHost(), db.getPort(), db.getDBName());
        Connection connection = null;
        try {
            //System.err.println("Try to connect at " + url);
            connection = db.getSQLDriver().getConnection(url, db.getUsername(), db.getPasswd());
            //System.err.println("Database connection established");
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.close();
                    System.err.println("Database connection terminated");
                } catch (Exception e) { /* ignore close errors */ }
            }
            System.err.println("Failed to connect at " + url);
            ex.printStackTrace(System.err);
        }
        if (connection == null) {
            System.err.println("Failed to connect at " + url);
        }

        // 导出我gexf文件
        //ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        //try {
        //    ec.exportFile(new File("io_gexf.gexf"));
        //} catch (IOException ex) {
        //    ex.printStackTrace();
        //    return;
        //}

        // 转换为json
        ExportJSON exportJSON = new ExportJSON();
        exportJSON.setWorkspace(workspace);
        String json = exportJSON.gephi2json();

        //Close connection
        if (connection != null) {
            try {
                connection.close();
                //System.err.println("Database connection terminated");
            } catch (Exception e) { /* ignore close errors */ }
        }
        return json;
    }

    public String getJsonTest() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

        //Import database
        EdgeListDatabaseImpl db = new EdgeListDatabaseImpl();
        db.setDBName("cobweb");
        db.setHost("10.0.0.156");
        db.setUsername("postgres");
        db.setPasswd("root");
        //db.setSQLDriver(new MySQLDriver());
        db.setSQLDriver(new PostgreSQLDriver());
        //db.setSQLDriver(new SQLServerDriver());
        db.setPort(5432);
        db.setNodeQuery("SELECT DISTINCT sender as id,sender as label FROM relationship UNION SELECT DISTINCT receiver as id,receiver as label  FROM relationship;");
        db.setEdgeQuery("SELECT sender AS source, receiver AS target, concat(topic,' ',begin_time) AS label, weight FROM relationship");
        ImporterEdgeList edgeListImporter = new ImporterEdgeList();
        Container container = importController.importDatabase(db, edgeListImporter);
        container.getLoader().setAllowAutoNode(false);      //Don't create missing nodes
        container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force UNDIRECTED

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);

        //See if graph is well imported
        UndirectedGraph graph = graphModel.getUndirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());

        //Layout - 100 Yifan Hu passes
        YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(2f));
        layout.setGraphModel(graphModel);
        layout.resetPropertiesValues();
        layout.initAlgo();
        for (int i = 0; i < 100 && layout.canAlgo(); i++) {
            layout.goAlgo();
        }
        layout.endAlgo();

        //Export X, Y position to the DB
        //Connect database
        String url = SQLUtils.getUrl(db.getSQLDriver(), db.getHost(), db.getPort(), db.getDBName());
        Connection connection = null;
        try {
            //System.err.println("Try to connect at " + url);
            connection = db.getSQLDriver().getConnection(url, db.getUsername(), db.getPasswd());
            //System.err.println("Database connection established");
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.close();
                    System.err.println("Database connection terminated");
                } catch (Exception e) { /* ignore close errors */ }
            }
            System.err.println("Failed to connect at " + url);
            ex.printStackTrace(System.err);
        }
        if (connection == null) {
            System.err.println("Failed to connect at " + url);
        }

        // 导出我gexf文件
        //ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        //try {
        //    ec.exportFile(new File("io_gexf.gexf"));
        //} catch (IOException ex) {
        //    ex.printStackTrace();
        //    return;
        //}

        // 转换为json
        ExportJSON exportJSON = new ExportJSON();
        exportJSON.setWorkspace(workspace);
        String json = exportJSON.gephi2json();

        //Close connection
        if (connection != null) {
            try {
                connection.close();
                //System.err.println("Database connection terminated");
            } catch (Exception e) { /* ignore close errors */ }
        }
        return json;
    }





    public String gephi2json() {
        Graph graph = null;
        String gephiJson = null;
        boolean exportVisible = false;

        try {
            GraphModel graphModel = workspace.getLookup().lookup(GraphModel.class);
            if (exportVisible) {
                graph = graphModel.getGraphVisible();
            } else {
                graph = graphModel.getGraph();
            }
            graph.readLock();

            //Count the number of tasks (nodes + edges) and start the progress
            int tasks = graph.getNodeCount() + graph.getEdgeCount();

            //FileWriter fwriter = new  FileWriter(writer);

            Gson gson = new Gson();
            EdgeColor colorMixer = new EdgeColor(EdgeColor.Mode.MIXED);

            //HashMap<String, String> nodeIdMap = new HashMap<String, String>();
            //int nodeId = 0;
            //EdgeColor colorMixer = new EdgeColor(EdgeColor.Mode.MIXED);
            //Write data.json


            Table attModel = graphModel.getNodeTable();
            HashSet<GraphElement> jNodes = new HashSet<GraphElement>();
            Node[] nodeArray = graph.getNodes().toArray();

            for (Node n : nodeArray) {

                String id = n.getId().toString();
                String label = n.getLabel();
                float x = n.x();
                float y = n.y();
                //float z=n.z();
                float size = n.size();


                    /*if (renumber) {
                     String newId=String.valueOf(nodeId);
                     nodeIdMap.put(id,newId);
                     id=newId;
                     nodeId++;
                     }*/

                String color = "rgb(" + (int) (n.r() * 255) + "," + (int) (n.g() * 255) + "," + (int) (n.b() * 255) + ")";

                GraphNode jNode = new GraphNode(id);
                jNode.setLabel(label);
                jNode.setX(x);
                jNode.setY(y);
                jNode.setSize(size);
                jNode.setColor(color);


                for (Column col : attModel) {
                    String name = col.getTitle();
                    String cid = col.getId();
                    if (cid.equalsIgnoreCase("id") || cid.equalsIgnoreCase("label")) {
                        continue;
                    }

                    Object valObj = n.getAttribute(col);
                    if (valObj == null) {
                        continue;
                    }
                    String val = valObj.toString();
                    jNode.putAttribute(name, val);

                }

                jNodes.add(jNode);
            }


            //Export edges. Progress is incremented at each step.
            HashSet<GraphElement> jEdges = new HashSet<GraphElement>();
            Edge[] edgeArray = graph.getEdges().toArray();

            for (Edge e : edgeArray) {
                String sourceId = e.getSource().getId().toString();
                String targetId = e.getTarget().getId().toString();

                GraphEdge jEdge = new GraphEdge(String.valueOf(e.getId()));
                jEdge.setFrom(sourceId);
                jEdge.setTo(targetId);
                jEdge.setSize(e.getWeight());
                jEdge.setLabel(e.getLabel());

                float r=e.r();
                float g=e.g();
                float b=e.b();

                Iterator<Column> eAttr = e.getAttributeColumns().iterator();
                while (eAttr.hasNext()) {
                    Column col = eAttr.next();
                    if (col.isProperty() || "weight".equalsIgnoreCase(col.getId())) {
                        //isProperty() excludes id, label, but not weight
                        continue;
                    }
                    String name = col.getTitle();
                    Object valObj = e.getAttribute(col);
                    if (valObj == null) {
                        continue;
                    }
                    String val = valObj.toString();
                    jEdge.putAttribute(name, val);
                }

                if (e.alpha()!=0) {
                    //color = "rgb(" + (int) (r* 255) + "," + (int) (g* 255) + "," + (int) (b* 255) + ")";
                } else {
                    //no colour has been set. Colour will be mix of connected nodes

                }
                Color color = new Color("#022240", "#d70007");
                jEdge.setColor(color);

                jEdges.add(jEdge);

            }

            HashMap<String, HashSet<GraphElement>> json = new HashMap<String, HashSet<GraphElement>>();
            json.put("nodes", jNodes);
            json.put("edges", jEdges);

            gephiJson = JSON.toJSONString(json, SerializerFeature.DisableCircularReferenceDetect);
        } catch (Exception e) {
            Logger.getLogger(ExportJSON.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (graph!=null) {
                graph.readUnlock();
            }
        }
        return gephiJson;
    }
}
