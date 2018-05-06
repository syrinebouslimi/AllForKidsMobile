/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Statistique;
import com.allforkids.Services.StatistiqueService;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Syrine
 */
public class ChartsForm {

    Form chartForm;
    List<Float> values = new ArrayList<>();
    List<String> nomEtab = new ArrayList<>();

   /* public ChartsForm() {

        chartForm = new Form("Statistiques");

        Toolbar.setGlobalToolbar(true);
        chartForm.setScrollableY(true);
        chartForm.setSmoothScrolling(true);

        chartForm.getToolbar().addCommandToLeftBar("Retour", null, ev -> {
            AdminForm adminform = new AdminForm();
            adminform.getAdminForm().showBack();

        });

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/allEtablissementWithRating");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                StatistiqueService ss = new StatistiqueService();
                List<Statistique> list = ss.getListStatistique(new String(con.getResponseData()));
                System.out.println("lll " + list);
                for (int i = 0; i < list.size(); i++) {
                    values.add(list.get(i).getAvgrating());
                    nomEtab.add(list.get(i).getNomEtab());

                }
                
                
                     int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        PieChart chart = new PieChart(buildCategoryDataset("Etablissement", values), renderer);
        ChartComponent c = new ChartComponent(chart);
       // chartForm.setLayout(new BorderLayout());
        chartForm.setLayout(new BorderLayout());
        chartForm.addComponent(BorderLayout.CENTER, c);
        chartForm.show();
                
            }
        });

   

    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, List<Float> values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (int i=0; i<values.size();i++) {
            series.add(nomEtab.get(i),values.get(i));
            System.out.println("vaaal "+series);
        }

        return series;
    }

   */
    
    public Form getChartForm() {
        return chartForm;
    }

    public void setChartForm(Form chartForm) {
        this.chartForm = chartForm;
    }
    
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, List<Float> values) {
        CategorySeries series = new CategorySeries(title);
        for (int i=0 ; i<values.size(); i++){
                  series.add(nomEtab.get(i), values.get(i));

        }
      

        return series;
    }

    public void createPieChartForm() {
        
        
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/allEtablissementWithRating");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                StatistiqueService ss = new StatistiqueService();
                List<Statistique> list = ss.getListStatistique(new String(con.getResponseData()));
                System.out.println("lll " + list);
                for (int i = 0; i < list.size(); i++) {
                    values.add(list.get(i).getAvgrating());
                    nomEtab.add(list.get(i).getNomEtab());

                }
                
                 // Generate the values

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN,
        ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN,
        ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Moyenne rating des etablissements", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        chartForm = new Form("Moyenne rating des etablissements");
        

        chartForm.setLayout(new BorderLayout());
        chartForm.addComponent(BorderLayout.CENTER, c); 
                chartForm.getToolbar().addCommandToLeftBar("Retour", null, ev -> {
            AdminForm adminform = new AdminForm();
            adminform.getAdminForm().showBack();

        });
                chartForm.show();
                
                
            }
               
            });
                
                 
        
        

       
        
        
    }

}
