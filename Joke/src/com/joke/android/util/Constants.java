package com.joke.android.util;

public class Constants {
    
    /**
     * ��������ַ
     */
    
    public static final String  Base_URL                       = "http://jokeweb.jd-app.com";
    public static final String  Base_Debug_URL                 ="http://192.168.1.109";
    public static final String  Base_IM_URL                    = Base_URL + "/signalr";
    public static final String  Base_HTTP_URL                  = Base_URL;
    
    // ͼƬ����Ŀ¼
    public static final String  IMAGELOADER_FOLDER_NOSLASH     = "/xmhouse/social/res/ImageLoader";
    
    /**
     * ����ģʽ���أ�����ʱ����رա�(false)
     */
    public static final boolean DEBUG                          = false;
    
    /**
     * ��ʾ
     */
    public static final int     POISEARCH                      = 1000;
    
    public static final int     ERROR                          = 1001;
    public static final int     FIRST_LOCATION                 = 1002;
    
    public static final int     ROUTE_START_SEARCH             = 2000;                             // ·���滮�������
    public static final int     ROUTE_END_SEARCH               = 2001;                             // ·���滮�������
    public static final int     ROUTE_SEARCH_RESULT            = 2002;                             // ·���滮���
    public static final int     ROUTE_SEARCH_ERROR             = 2004;                             // ·���滮����ʼ�������쳣
                                                                                                    
    public static final int     REOCODER_RESULT                = 3000;                             // ���������
    public static final int     DIALOG_LAYER                   = 4000;
    public static final int     POISEARCH_NEXT                 = 5000;
    
    public static final int     BUSLINE_RESULT                 = 6000;
    public static final int     BUSLINE_DETAIL_RESULT          = 6001;
    public static final int     BUSLINE_ERROR_RESULT           = 6002;
    
    // �л�
    public final static int     KEY_FIRST_INVERSE              = 1;
    public final static int     KEY_FIRST_CLOCKWISE            = 2;
    public final static int     KEY_SECOND_INVERSE             = 3;
    public final static int     KEY_SECOND_CLOCKWISE           = 4;
    
    public final static int     REQUEST_CODE_SEEHOUSE_REGISTER = 10;
    public final static int     REQUEST_CODE_SUBMIT_COMMENT    = 11;
    public final static int     REQUEST_CODE_SEEHOUSE_DETAIL   = 12;
    
    public static String        ADDRESS                        = "address";
    
    public static String        LONGTITUDE                     = "longtitude";
    public static String        LATITUDE                       = "latitude";
}
