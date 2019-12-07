package com.hgutil.data;

/**
 * author:      hgrein<BR>
 * date:        Jun 8, 2004<BR>
 * Package:     com.hgutil.data<BR>
 * File Name:   StockMarketTypes.java<BR>
 * Type Name:   StockMarketTypes<BR>
 * Description: Interface class describing various types for a Stock Market and Options
 */
public interface StockMarketTypes
{

  /** Field <code>DEBUG</code> : boolean */
  public boolean DEBUG = false;

  /** Field <code>NONE</code> : int */
  public int NONE = 0;
  /** Field <code>STOCK</code> : int */
  public int STOCK = 1;
  /** Field <code>FUND</code> : int */
  public int FUND = 2;
  /** Field <code>INDEX</code> : int */
  public int INDEX = 3;
  /** Field <code>CALL</code> : int */
  public int CALL = 4;
  /** Field <code>PUT</code> : int */
  public int PUT = 5;
  /** Field <code>OPTION_SYMBOL_LENGTH</code> : int */
  public int OPTION_SYMBOL_LENGTH = 5;
  
  /** Field <code>SPREAD_2_50</code> : int */
  public int SPREAD_2_50 = 0;
  /** Field <code>SPREAD_5_00</code> : int */
  public int SPREAD_5_00 = 1;
  /** Field <code>SPREAD_10_00</code> : int */
  public int SPREAD_10_00 = 2;

  /** Field <code>TYPESARR</code> : SVPair[] */
  public SVPair[] TYPESARR =
    {
      new SVPair("NONE", NONE),
      new SVPair("STOCK", STOCK),
      new SVPair("FUND", FUND),
      new SVPair("INDEX", INDEX),
      new SVPair("CALL", CALL),
      new SVPair("PUT", PUT)};

  /** Field <code>JAN</code> : int */
  public int JAN = 0;
  /** Field <code>FEB</code> : int */
  public int FEB = 1;
  /** Field <code>MAR</code> : int */
  public int MAR = 2;
  /** Field <code>APR</code> : int */
  public int APR = 3;
  /** Field <code>MAY</code> : int */
  public int MAY = 4;
  /** Field <code>JUN</code> : int */
  public int JUN = 5;
  /** Field <code>JUL</code> : int */
  public int JUL = 6;
  /** Field <code>AUG</code> : int */
  public int AUG = 7;
  /** Field <code>SEP</code> : int */
  public int SEP = 8;
  /** Field <code>OCT</code> : int */
  public int OCT = 9;
  /** Field <code>NOV</code> : int */
  public int NOV = 10;
  /** Field <code>DEC</code> : int */
  public int DEC = 11;

  /** Field <code>JAN_SZ</code> : String */
  public String JAN_SZ = "Jan";
  /** Field <code>FEB_SZ</code> : String */
  public String FEB_SZ = "Feb";
  /** Field <code>MAR_SZ</code> : String */
  public String MAR_SZ = "Mar";
  /** Field <code>APR_SZ</code> : String */
  public String APR_SZ = "Apr";
  /** Field <code>MAY_SZ</code> : String */
  public String MAY_SZ = "May";
  /** Field <code>JUN_SZ</code> : String */
  public String JUN_SZ = "Jun";
  /** Field <code>JUL_SZ</code> : String */
  public String JUL_SZ = "Jul";
  /** Field <code>AUG_SZ</code> : String */
  public String AUG_SZ = "Aug";
  /** Field <code>SEP_SZ</code> : String */
  public String SEP_SZ = "Sep";
  /** Field <code>OCT_SZ</code> : String */
  public String OCT_SZ = "Oct";
  /** Field <code>NOV_SZ</code> : String */
  public String NOV_SZ = "Nov";
  /** Field <code>DEC_SZ</code> : String */
  public String DEC_SZ = "Dec";

  /** Field <code>MONTHSARR</code> : SVPair[] */
  public SVPair[] MONTHSARR =
    {
      new SVPair(JAN_SZ, JAN),
      new SVPair(FEB_SZ, FEB),
      new SVPair(MAR_SZ, MAR),
      new SVPair(APR_SZ, APR),
      new SVPair(MAY_SZ, MAY),
      new SVPair(JUN_SZ, JUN),
      new SVPair(JUL_SZ, JUL),
      new SVPair(AUG_SZ, AUG),
      new SVPair(SEP_SZ, SEP),
      new SVPair(OCT_SZ, OCT),
      new SVPair(NOV_SZ, NOV),
      new SVPair(DEC_SZ, DEC)};

  /** Field <code>A</code> : int */
  // Call Month Codes

  public int A = JAN;
  /** Field <code>B</code> : int */
  public int B = FEB;
  /** Field <code>C</code> : int */
  public int C = MAR;
  /** Field <code>D</code> : int */
  public int D = APR;
  /** Field <code>E</code> : int */
  public int E = MAY;
  /** Field <code>F</code> : int */
  public int F = JUN;
  /** Field <code>G</code> : int */
  public int G = JUL;
  /** Field <code>H</code> : int */
  public int H = AUG;
  /** Field <code>I</code> : int */
  public int I = SEP;
  /** Field <code>J</code> : int */
  public int J = OCT;
  /** Field <code>K</code> : int */
  public int K = NOV;
  /** Field <code>L</code> : int */
  public int L = DEC;

  /** Field <code>CALLMONTHARR</code> : SVPair[] */
  public SVPair[] CALLMONTHARR =
    {
      new SVPair("A", A),
      new SVPair("B", B),
      new SVPair("C", C),
      new SVPair("D", D),
      new SVPair("E", E),
      new SVPair("F", F),
      new SVPair("G", G),
      new SVPair("H", H),
      new SVPair("I", I),
      new SVPair("J", J),
      new SVPair("K", K),
      new SVPair("L", L)};

  /** Field <code>M</code> : int */
  // Put Month Codes

  public int M = JAN;
  /** Field <code>N</code> : int */
  public int N = FEB;
  /** Field <code>O</code> : int */
  public int O = MAR;
  /** Field <code>P</code> : int */
  public int P = APR;
  /** Field <code>Q</code> : int */
  public int Q = MAY;
  /** Field <code>R</code> : int */
  public int R = JUN;
  /** Field <code>S</code> : int */
  public int S = JUL;
  /** Field <code>T</code> : int */
  public int T = AUG;
  /** Field <code>U</code> : int */
  public int U = SEP;
  /** Field <code>V</code> : int */
  public int V = OCT;
  /** Field <code>W</code> : int */
  public int W = NOV;
  /** Field <code>X</code> : int */
  public int X = DEC;

  /** Field <code>PUTMONTHARR</code> : SVPair[] */
  public SVPair[] PUTMONTHARR =
    {
      new SVPair("M", M),
      new SVPair("N", N),
      new SVPair("O", O),
      new SVPair("P", P),
      new SVPair("Q", Q),
      new SVPair("R", R),
      new SVPair("S", S),
      new SVPair("T", T),
      new SVPair("U", U),
      new SVPair("V", V),
      new SVPair("W", W),
      new SVPair("X", X)};

  /** Field <code>SPC_A</code> : String[] */
  public String[] SPC_A = { "5", "105", "205", "305" };
  /** Field <code>SPC_B</code> : String[] */
  public String[] SPC_B = { "10", "110", "210", "310" };
  /** Field <code>SPC_C</code> : String[] */
  public String[] SPC_C = { "15", "115", "215", "315" };
  /** Field <code>SPC_D</code> : String[] */
  public String[] SPC_D = { "20", "120", "220", "320" };
  /** Field <code>SPC_E</code> : String[] */
  public String[] SPC_E = { "25", "125", "225", "325" };
  /** Field <code>SPC_F</code> : String[] */
  public String[] SPC_F = { "30", "130", "230", "330" };
  /** Field <code>SPC_G</code> : String[] */
  public String[] SPC_G = { "35", "135", "235", "335" };
  /** Field <code>SPC_H</code> : String[] */
  public String[] SPC_H = { "40", "140", "240", "340" };
  /** Field <code>SPC_I</code> : String[] */
  public String[] SPC_I = { "45", "145", "245", "345" };
  /** Field <code>SPC_J</code> : String[] */
  public String[] SPC_J = { "50", "150", "250", "350" };
  /** Field <code>SPC_K</code> : String[] */
  public String[] SPC_K = { "55", "155", "255", "355" };
  /** Field <code>SPC_L</code> : String[] */
  public String[] SPC_L = { "60", "160", "260", "360" };
  /** Field <code>SPC_M</code> : String[] */
  public String[] SPC_M = { "65", "165", "265", "365" };
  /** Field <code>SPC_N</code> : String[] */
  public String[] SPC_N = { "70", "170", "270", "370" };
  /** Field <code>SPC_O</code> : String[] */
  public String[] SPC_O = { "75", "175", "275", "375" };
  /** Field <code>SPC_P</code> : String[] */
  public String[] SPC_P = { "80", "180", "280", "380" };
  /** Field <code>SPC_Q</code> : String[] */
  public String[] SPC_Q = { "85", "185", "285", "385" };
  /** Field <code>SPC_R</code> : String[] */
  public String[] SPC_R = { "90", "190", "290", "390" };
  /** Field <code>SPC_S</code> : String[] */
  public String[] SPC_S = { "95", "195", "295", "395" };
  /** Field <code>SPC_T</code> : String[] */
  public String[] SPC_T = { "100", "200", "200", "300" };
  /** Field <code>SPC_U</code> : String[] */
  public String[] SPC_U = { "7.5", "37.5", "67.5", "97.5" };
  /** Field <code>SPC_V</code> : String[] */
  public String[] SPC_V = { "12.5", "42.5", "72.5", "102.5" };
  /** Field <code>SPC_W</code> : String[] */
  public String[] SPC_W = { "17.5", "47.5", "77.5", "107.5" };
  /** Field <code>SPC_X</code> : String[] */
  public String[] SPC_X = { "22.5", "52.5", "82.5", "112.5" };
  /** Field <code>SPC_Y</code> : String[] */
  public String[] SPC_Y = { "27.5", "57.5", "87.5", "117.5" };
  /** Field <code>SPC_Z</code> : String[] */
  public String[] SPC_Z = { "32.5", "62.5", "92.5", "122.5" };

  /** Field <code>SPCARRAY</code> : String[][] */
  public String[][] SPCARRAY =
    {
      SPC_A,
      SPC_B,
      SPC_C,
      SPC_D,
      SPC_E,
      SPC_F,
      SPC_G,
      SPC_H,
      SPC_I,
      SPC_J,
      SPC_K,
      SPC_L,
      SPC_M,
      SPC_N,
      SPC_O,
      SPC_P,
      SPC_Q,
      SPC_R,
      SPC_S,
      SPC_T,
      SPC_U,
      SPC_V,
      SPC_W,
      SPC_X,
      SPC_Y,
      SPC_Z };

  /** Field <code>DAILY</code> : String */
  public String DAILY = "d";
  /** Field <code>WEEKLY</code> : String */
  public String WEEKLY = "w";
  /** Field <code>MONTHLY</code> : String */
  public String MONTHLY = "m";

  /** Field <code>FREQLETTERSARR</code> : SVPair[] */
  public SVPair[] FREQLETTERSARR = { new SVPair(DAILY, 0), new SVPair(WEEKLY, 1), new SVPair(MONTHLY, 2)};

  /** Field <code>DAILY_SZ</code> : String */
  public String DAILY_SZ = "Daily";
  /** Field <code>WEEKLY_SZ</code> : String */
  public String WEEKLY_SZ = "Weekly";
  /** Field <code>MONTHLY_SZ</code> : String */
  public String MONTHLY_SZ = "Monthly";

  /** Field <code>FREQNAMESARR</code> : SVPair[] */
  public SVPair[] FREQNAMESARR = { new SVPair(DAILY_SZ, 0), new SVPair(WEEKLY_SZ, 1), new SVPair(MONTHLY_SZ, 2)};

}