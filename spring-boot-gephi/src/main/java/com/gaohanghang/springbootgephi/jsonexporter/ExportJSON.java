package com.gaohanghang.springbootgephi.jsonexporter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gaohanghang.springbootgephi.common.enums.Group;
import com.gaohanghang.springbootgephi.jsonexporter.model.Color;
import com.gaohanghang.springbootgephi.jsonexporter.model.GraphEdge;
import com.gaohanghang.springbootgephi.jsonexporter.model.GraphElement;
import com.gaohanghang.springbootgephi.jsonexporter.model.GraphNode;
import com.google.gson.Gson;
import org.gephi.graph.api.*;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.GraphExporter;
import org.gephi.preview.types.EdgeColor;
import org.gephi.project.api.Workspace;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;

import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportJSON implements GraphExporter, LongTask, CharacterExporter {

    String gephiJson;
    private boolean exportVisible = false;
    private Workspace workspace;
    private Writer writer;
    private ProgressTicket progress;
    private boolean cancel = false;
    //private File path;

    public String gephi2json() {
        Graph graph = null;

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
                Progress.start(progress, tasks);

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
                    String color = "rgb(" + (int) (n.r() * 255) + "," + (int) (n.g() * 255) + "," + (int) (n.b() * 255) + ")";

                    /*if (renumber) {
                     String newId=String.valueOf(nodeId);
                     nodeIdMap.put(id,newId);
                     id=newId;
                     nodeId++;
                     }*/

                    GraphNode jNode = new GraphNode(id);
                    jNode.setLabel(label);
                    jNode.setX(x);
                    jNode.setY(y);
                    jNode.setSize(size);
                    jNode.setColor(color);
                    jNode.setGroup(Group.QQ.getCode());


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

                    Progress.progress(progress);
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
                    Color color = new Color("black", "red");
                    jEdge.setColor(color);

                    jEdges.add(jEdge);

                    Progress.progress(progress);
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

    @Override
    public boolean execute() {
      return false;
    }

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public boolean isExportVisible() {
        return exportVisible;
    }

    @Override
    public void setExportVisible(boolean exportVisible) {
        this.exportVisible = exportVisible;
    }

    @Override
    public boolean cancel() {
        cancel = true;
        return true;
    }

    @Override
    public void setProgressTicket(ProgressTicket pt) {
        this.progress = pt;
    }
}
