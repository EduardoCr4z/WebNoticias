
import Controlador.ControllerManagedBeanNoticias;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.ItemSelectEvent;


import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;







/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author EduardoCruz
 */
@Named("chartJsView")
@RequestScoped
public class ChartJsView implements Serializable {

    private PieChartModel pieModel;
    private ControllerManagedBeanNoticias control;
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        createPieModel();
        createDonutModel();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        for(int i=0;i<control.ListaPaises.size();i++){
            int h=0;
            for(int j=0;j<control.ListaPaises.get(i).size();j++){//{{es,es},{al,al,al}}
                h=h+1;
            }
            values.add(h);
            System.out.println(values);
        }
        
        dataSet.setData(values);
        List<String> bgColors = new ArrayList<>();
        
        for(int i=0;i<control.ListaPaises.size();i++){
            int numero1 = (int)(Math.random()*250+0);
            int numero2 = (int)(Math.random()*250+0);
            int numero3 = (int)(Math.random()*250+0);
            bgColors.add("rgb("+String.valueOf(numero1)+","+String.valueOf(numero2)+","+String.valueOf(numero3)+")");
            System.out.println(bgColors);
        }
        
        
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        ArrayList<String> h=new ArrayList<>();
        String m;
        for(int i=0;i<ControllerManagedBeanNoticias.ListaPaises.size();i++){
            int j=0;
            h=ControllerManagedBeanNoticias.ListaPaises.get(i);//{{es,es}{al,al}}
            m=h.get(j);
            labels.add(m);
            
            
           
            System.out.println(labels);
        }
        data.setLabels(labels);

        pieModel.setData(data);
    }

   
    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        
        for(int i=0;i<control.ListaCategorias.size();i++){
            int h=0;
            for(int j=0;j<control.ListaCategorias.get(i).size();j++){
                h=h+1;
            }
            values.add(h);
            System.out.println(values);
        }
        
        dataSet.setData(values);
        List<String> bgColors = new ArrayList<>();
        
        for(int i=0;i<control.ListaCategorias.size();i++){
            int numero1 = (int)(Math.random()*250+0);
            int numero2 = (int)(Math.random()*250+0);
            int numero3 = (int)(Math.random()*250+0);
            bgColors.add("rgb("+String.valueOf(numero1)+","+String.valueOf(numero2)+","+String.valueOf(numero3)+")");
            System.out.println(bgColors);
        }
        
        
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        ArrayList<String> h=new ArrayList<>();
        String m;
        for(int i=0;i<ControllerManagedBeanNoticias.ListaCategorias.size();i++){
            int j=0;
            h=ControllerManagedBeanNoticias.ListaCategorias.get(i);
            m=h.get(j);
            labels.add(m);
            
            
           
            System.out.println(labels);
        }
        
        data.setLabels(labels);

        donutModel.setData(data);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }   
}
