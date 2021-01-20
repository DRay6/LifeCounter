package ch.helsana.lifecounter;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link LifeCounterWidgetConfigureActivity LifeCounterWidgetConfigureActivity}
 */
public class LifeCounterWidget extends AppWidgetProvider {

    private LifePoints lifePoints;
    private static final String OnClickPlus100 = "onClickPlus100";
    private static final String OnClickPlus500 = "onClickPlus500";
    private static final String OnClickPlus1000 = "onClickPlus1000";
    private static final String OnClickMinus100 = "onClickMinus100";
    private static final String OnClickMinus500 = "onClickMinus500";
    private static final String OnClickMinus1000 = "onClickMinus1000";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //CharSequence widgetText = LifeCounterWidgetConfigureActivity.loadTitlePref(context, appWidgetId);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.life_counter_widget);
        views.setTextViewText(R.id.lifepoints_text, String.valueOf(LifePoints.getSingletonInstance().getLp()));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        lifePoints = LifePoints.getSingletonInstance();

        for (int appWidgetId : appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.life_counter_widget);
            views.setOnClickPendingIntent(R.id.btPlus100,
                    getPendingSelfIntent(context, OnClickPlus100, appWidgetId));
            views.setOnClickPendingIntent(R.id.btPlus500,
                    getPendingSelfIntent(context, OnClickPlus500, appWidgetId));
            views.setOnClickPendingIntent(R.id.btPlus1000,
                    getPendingSelfIntent(context, OnClickPlus1000, appWidgetId));
            views.setOnClickPendingIntent(R.id.btMin100,
                    getPendingSelfIntent(context, OnClickMinus100, appWidgetId));
            views.setOnClickPendingIntent(R.id.btMin500,
                    getPendingSelfIntent(context, OnClickMinus500, appWidgetId));
            views.setOnClickPendingIntent(R.id.btMin1000,
                    getPendingSelfIntent(context, OnClickMinus1000, appWidgetId));

            views.setTextViewText(R.id.lifepoints_text, String.valueOf(lifePoints.getLp()));
            appWidgetManager.updateAppWidget(appWidgetId, views);
            //updateAppWidget(context, appWidgetManager, appWidgetId);

        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            LifeCounterWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        lifePoints = LifePoints.getSingletonInstance();
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.life_counter_widget);

        lifePoints = LifePoints.getSingletonInstance();

        super.onReceive(context, intent);
        switch (intent.getAction()) {
            case OnClickPlus100:
                lifePoints.modifyLifePoints(100);
                break;
            case OnClickPlus500:
                lifePoints.modifyLifePoints(500);
                break;
            case OnClickPlus1000:
                lifePoints.modifyLifePoints(1000);
                break;
            case OnClickMinus100:
                lifePoints.modifyLifePoints(-100);
                break;
            case OnClickMinus500:
                lifePoints.modifyLifePoints(-500);
                break;
            case OnClickMinus1000:
                lifePoints.modifyLifePoints(-1000);
                break;
        }


    }

    ;

    protected PendingIntent getPendingSelfIntent(Context context, String action, int widgetId) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.life_counter_widget);
        lifePoints.modifyLifePoints(100);
        views.setTextViewText(R.id.lifepoints_text, String.valueOf(lifePoints.getLp()));


        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        int lp = lifePoints.getLp();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        views.setTextViewText(R.id.lifepoints_text, String.valueOf(lp));
        appWidgetManager.updateAppWidget(widgetId, views);

        return pendingIntent;
    }
}