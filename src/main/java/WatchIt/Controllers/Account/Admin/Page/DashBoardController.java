package WatchIt.Controllers.Account.Admin.Page;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import src.AccountControl.Admin;
import src.DataBase.DataBase;

import java.util.Timer;
import java.util.TimerTask;

public class DashBoardController {

    public Text AccountsNumber;


    public Text Admins;


    public Text Profits;


    public BarChart<String, Long> RevenueChart,plansAnalysis;

    public void initialize(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            long acc=DataBase.getInstance().usersData.getData().size(),adm= DataBase.getInstance().adminsData.getData().size(),Revenue=Admin.calculateRevenue(),cnt =0,cnt1=0,cnt2=0;
            public void run() {
                if(cnt<=acc)
                    AccountsNumber.setText(Long.valueOf(Math.min(acc,cnt++)).toString());
                if(cnt1<=Revenue)
                    Profits.setText(Long.valueOf( Math.min(Revenue,cnt1+=100)).toString());
                if(cnt2<=adm)
                    Admins.setText(Long.valueOf( Math.min(adm,cnt2++)).toString());
                if(cnt1>Revenue&&cnt>acc&&cnt2>adm) {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, 200);
        RevenueChart.setTitle("Revenue");
        XYChart.Series<String, Long> series = new XYChart.Series<>();
        series.setName("Revenue");
        series.getData().add(new XYChart.Data<>("Dec",Admin.CalculateRevenue(2024,11)));
        RevenueChart.getData().add(series);;
        series = new XYChart.Series<>();
        series.setName("Basic");
        series.getData().add(new XYChart.Data<>("Basic",Admin.plansAnalysis().get(0)));
        XYChart.Series<String,Long> Standard = new XYChart.Series<>();
        Standard.getData().add(new XYChart.Data<>("Standard",Admin.plansAnalysis().get(1)));
        Standard.setName("Standard");
        XYChart.Series<String,Long> Premium = new XYChart.Series<>();
        Premium.getData().add(new XYChart.Data<>("Standard",Admin.plansAnalysis().get(1)));
        Premium.setName("Premium");
        Premium.getData().add(new XYChart.Data<>("Premium",Admin.plansAnalysis().get(2)));
        plansAnalysis.getData().addAll(series,Standard,Premium);
    }
}
