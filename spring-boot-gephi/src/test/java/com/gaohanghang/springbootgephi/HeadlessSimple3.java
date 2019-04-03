///*
//Copyright 2008-2010 Gephi
//Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
//Website : http://www.gephi.org
//
//This file is part of Gephi.
//
//Gephi is free software: you can redistribute it and/or modify
//it under the terms of the GNU Affero General Public License as
//published by the Free Software Foundation, either version 3 of the
//License, or (at your option) any later version.
//
//Gephi is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU Affero General Public License for more details.
//
//You should have received a copy of the GNU Affero General Public License
//along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
// */
//package com.gaohanghang.springbootgephi;
//
//import org.gephi.graph.api.GraphController;
//import org.gephi.graph.api.GraphModel;
//import org.gephi.graph.api.Node;
//import org.gephi.graph.api.UndirectedGraph;
//import org.gephi.io.database.drivers.PostgreSQLDriver;
//import org.gephi.io.database.drivers.SQLUtils;
//import org.gephi.io.exporter.api.ExportController;
//import org.gephi.io.exporter.spi.GraphExporter;
//import org.gephi.io.exporter.spi.GraphFileExporterBuilder;
//import org.gephi.io.importer.api.Container;
//import org.gephi.io.importer.api.EdgeDirectionDefault;
//import org.gephi.io.importer.api.ImportController;
//import org.gephi.io.importer.plugin.database.EdgeListDatabaseImpl;
//import org.gephi.io.importer.plugin.database.ImporterEdgeList;
//import org.gephi.io.processor.plugin.DefaultProcessor;
//import org.gephi.layout.plugin.force.StepDisplacement;
//import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
//import org.gephi.project.api.ProjectController;
//import org.gephi.project.api.Workspace;
//import org.openide.util.Lookup;
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
// * This demo shows several actions done with the toolkit, aiming to do a
// * complete chain, from data import to results.
// * <p>
// * This demo shows the following steps:
// * <ul><li>Create a project and a workspace, it is mandatory.</li>
// * <li>Import the <code>polblogs.gml</code> graph file in an import
// * container.</li>
// * <li>Append the container to the main graph structure.</li>
// * <li>Filter the graph, using <code>DegreeFilter</code>.</li>
// * <li>Run layout manually.</li>
// * <li>Compute graph distance metrics.</li>
// * <li>Rank color by degree values.</li>
// * <li>Rank size by centrality values.</li>
// * <li>Configure preview to display labels and mutual edges differently.</li>
// * <li>Export graph as PDF.</li></ul>
// *
// * @author Mathieu Bastian
// */
//public class HeadlessSimple3 {
//
//    public void script() throws IOException {
//
//        //Init a project - and therefore a workspace
//        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
//        pc.newProject();
//        Workspace workspace = pc.getCurrentWorkspace();
//
//        //Get controllers and models
//        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
//        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
//
//        //Import database
//        EdgeListDatabaseImpl db = new EdgeListDatabaseImpl();
//        db.setDBName("cobweb");
//        db.setHost("10.0.0.156");
//        db.setUsername("postgres");
//        db.setPasswd("root");
////        db.setSQLDriver(new MySQLDriver());
//        db.setSQLDriver(new PostgreSQLDriver());
//        //db.setSQLDriver(new SQLServerDriver());
//        db.setPort(5432);
//        db.setNodeQuery("SELECT DISTINCT sender as id,sender as label FROM relationship UNION SELECT DISTINCT receiver as id,receiver as label  FROM relationship;");
//        db.setEdgeQuery("SELECT sender AS source, receiver AS target, concat(topic,' ',begin_time) AS label, weight FROM relationship");
//        ImporterEdgeList edgeListImporter = new ImporterEdgeList();
//        Container container = importController.importDatabase(db, edgeListImporter);
//        container.getLoader().setAllowAutoNode(false);      //Don't create missing nodes
//        container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force UNDIRECTED
//
//        //Append imported data to GraphAPI
//        importController.process(container, new DefaultProcessor(), workspace);
//
//        //See if graph is well imported
//        UndirectedGraph graph = graphModel.getUndirectedGraph();
//        System.out.println("Nodes: " + graph.getNodeCount());
//        System.out.println("Edges: " + graph.getEdgeCount());
//
//        //Layout - 100 Yifan Hu passes
//        YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(2f));
//        layout.setGraphModel(graphModel);
//        layout.resetPropertiesValues();
//        layout.initAlgo();
//        for (int i = 0; i < 100 && layout.canAlgo(); i++) {
//            layout.goAlgo();
//        }
//        layout.endAlgo();
//
//        //Export X, Y position to the DB
//        //Connect database
//        String url = SQLUtils.getUrl(db.getSQLDriver(), db.getHost(), db.getPort(), db.getDBName());
//        Connection connection = null;
//        try {
//            //System.err.println("Try to connect at " + url);
//            connection = db.getSQLDriver().getConnection(url, db.getUsername(), db.getPasswd());
//            //System.err.println("Database connection established");
//        } catch (SQLException ex) {
//            if (connection != null) {
//                try {
//                    connection.close();
//                    System.err.println("Database connection terminated");
//                } catch (Exception e) { /* ignore close errors */ }
//            }
//            System.err.println("Failed to connect at " + url);
//            ex.printStackTrace(System.err);
//        }
//        if (connection == null) {
//            System.err.println("Failed to connect at " + url);
//        }
//
//        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
//        try {
//            ec.exportFile(new File("io_gexf.gexf"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return;
//        }
//
//        // 导出为json
//        JSONExporterBuilder jsonExporterBuilder = new JSONExporterBuilder();
//        JSONExporter jsonExporter = new JSONExporter();
//        Writer writer = new FileWriter(new File("test.json"));
//        jsonExporter.setWorkspace(workspace);
//        jsonExporter.setWriter(writer);
//        jsonExporter.execute();
//
//        writer.flush();
//        writer.close(); //close write
//
//        //Close connection
//        if (connection != null) {
//            try {
//                connection.close();
//                //System.err.println("Database connection terminated");
//            } catch (Exception e) { /* ignore close errors */ }
//        }
//    }
//}
