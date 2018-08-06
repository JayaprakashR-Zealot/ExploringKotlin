package com.example.learnkotlin;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateARActivity extends AppCompatActivity {

    TextView textViewDateARValue, textViewDateARLYValue;
    public static final String ISO_DATE_TIME_FORMAT = "EEEE dd MMM, yyyy";

    static boolean isLangSet = false;

    private String[] RTLSupportedLocales = {"ar", "ar-ae", "ar-bh", "ar-dz", "ar-eg", "ar-iq", "ar-jo",
            "ar-kw", "ar-lb", "ar-ly", "ar-ma", "ar-om", "ar-qa", "ar-sa", "ar-sy", "ar-tn", "ar-ye"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_ar);
        textViewDateARValue = (TextView) findViewById(R.id.textViewDateARValue);
        textViewDateARLYValue = (TextView) findViewById(R.id.textViewDateARLYValue);
        //setLayoutDirection();

        //textViewDateARValue.setText(getCurrentDateUTC());
        //textViewDateARLYValue.setText(getAR_LYDateUTC());
        getARRegionDates();
    }

    private void setLayoutDirection() {
        Locale locale = new Locale("ar", "LY");
        if (null != locale) {
            Locale.setDefault(locale);
            Configuration configuration = getApplicationContext().getResources().getConfiguration();
            configuration.setLayoutDirection(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale);
            } else {
                configuration.locale = locale;
            }
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            getApplicationContext().getResources().updateConfiguration(configuration, getApplicationContext().getResources().getDisplayMetrics());
            if (!isLangSet) {
                isLangSet = true;
                finish();
                startActivity(getIntent());
            }
        }
    }

    public static String getCurrentDateUTC() {
        String result;
        //DateFormat mISODateTimeFormatted = new SimpleDateFormat(ISO_DATE_TIME_FORMAT, new Locale("ar"));
        DateFormat mISODateTimeFormatted = new SimpleDateFormat(ISO_DATE_TIME_FORMAT);
        result = mISODateTimeFormatted.format(new Date());
        return result;
    }

    public static String getAR_LYDateUTC() {
        String result;
        //DateFormat mISODateTimeFormatted = new SimpleDateFormat(ISO_DATE_TIME_FORMAT, new Locale("ar","LY"));
        DateFormat mISODateTimeFormatted = new SimpleDateFormat(ISO_DATE_TIME_FORMAT);
        result = mISODateTimeFormatted.format(new Date());
        return result;
    }

    public void getARRegionDates() {
        StringBuffer strAllDate = new StringBuffer ("Dates in all Arabian region");
        strAllDate.append("\n");
        for (int i=0;i<RTLSupportedLocales.length;i++){
            String result;
            Locale locale = getLocaleFromLanguageCode(RTLSupportedLocales[i]);
            DateFormat mISODateTimeFormatted = new SimpleDateFormat(ISO_DATE_TIME_FORMAT, locale);
            result = mISODateTimeFormatted.format(new Date());
            String date = RTLSupportedLocales[i]+" - "+result;
            strAllDate.append(date);
            strAllDate.append("\n");
        }
        textViewDateARLYValue.setText(strAllDate);
    }

    public static Locale getLocaleFromLanguageCode(String languageCode) {
        Locale locale;
        String strLanguageCode;
        String strCountryCode;
        if (null != languageCode && !languageCode.isEmpty() && languageCode.length() >= 2) {
            if (languageCode.contains("_")) {
                String[] language = languageCode.split("_");
                strLanguageCode = language[0];
                strCountryCode = language[1];
                locale = new Locale(strLanguageCode, strCountryCode);
            } else if (languageCode.contains("-")) {
                String[] language = languageCode.split("-");
                strLanguageCode = language[0];
                strCountryCode = language[1];
                locale = new Locale(strLanguageCode, strCountryCode);
            } else {
                strLanguageCode = languageCode;
                locale = new Locale(strLanguageCode);
            }
            return locale;
        } else {
            return Locale.getDefault();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isLangSet = false;
    }
}
