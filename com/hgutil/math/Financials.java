package com.hgutil.math;

/**
 * @author:     Hans-Jurgen Greiner<BR>
 * date:        Oct 24, 2001<BR>
 * Package:     com.hgutil.math<BR>
 * File Name:   Financials.java<BR>
 * Type Name:   Financials<BR>
 * Description: 
 * <font face="Times New Roman" size=+1>The Greeks</font><BR>
 * The <B>Greeks</B> are a collection of statistical values (expressed as percentages) 
 * that give the investor a better overall view of how a stock has been performing. These statistical 
 * values can be helpful in deciding what options strategies are best to use. The investor should 
 * remember that statistics show trends based on past performance. It is not guaranteed that the 
 * future performance of the stock will behave according to the historical numbers. These trends can 
 * change drastically based on new stock performance. <BR>
 * The Greeks and the associated derivatives are:
 * <UL>
 * <LI>Delta (price of underlying)</LI>
 * <LI>Gamma (2nd derivative of price)</LI>
 * <LI>Vega (volatility) percent</LI>
 * <LI>Theta (time)</LI>
 * <LI>Rho (risk free interest rate) percent</LI>
 * </UL>
 * <font face="Times New Roman" size=+1>Strengths and Weaknesses of the Greeks</font><BR>
 * The concept of using partial derivatives in pricing and hedging options and other financial
 * instruments is well known and mathematically correct. It is necessary to evaluate some of these
 * derivatives to understand the risk in a portfolio. However, the partial derivatives of option 
 * prices depend on the underlying distribution of price movements, and on price volatility of 
 * the underlying financial instruments. The weakness comes in using statistical models to 
 * calculate these values, which make assumptions that have been disproved from historical data.<BR> 
 * For example, it has been shown that stock market returns do not have a lognormal distribution; 
 * returns have much fatter tails, and return volatility varies by the length of the 
 * period measured. These models (lognormal) have worked reasonably well for some purposes, 
 * especially with adjustments to improve the fit to market prices. The practitioner needs to keep 
 * in mind the implicit assumptions when he draws inference from these calculations. It is always 
 * appropriate to compare derivatives (delta) from actual market prices to those obtained 
 * from the statistical models.<BR>
 */
public class Financials
{
  /** Field <code>ROUNDER_VALUE</code> : double */
  private static final double ROUNDER_VALUE = 10000.0;
  /** Field <code>DAYS_IN_YEAR</code> : double */
  private static final double DAYS_IN_YEAR = 365.0;

  /**
   * Method ND. Normal distribution gives the probability that a standard normal variate assumes a 
   * value in the interval [0,z].  
   * @param x - double
   * @return double
   */
  public static double normalDist( double x )
  {
    return 1.0 / Math.sqrt(2.0 * Math.PI) * Math.exp(-0.5 * x);
  }

  /**
   * Method CND. Cummulative Normal Distribution Function which gives the probability that a 
   * variate will assume a value <B><FONT FACE="Symbol">&#163;</FONT>&nbsp;Z</B>, is then the integral of the normal distribution
   * For this method there is no <BR><I><B>erf</B></I> 
   * (sometimes called error function), Since erf cannot be expressed in terms of finite additions, 
   * subtractions, multiplications, It must be either computed numerically or otherwise approximated.  
   * In this case it is approximated.
   * @param X
   * @return double
   */
  public static double cummulativeNormalDist( double X )
  {
    double L, K, w;
    double a1 = 0.31938153, a2 = -0.356563782, a3 = 1.781477937, a4 = -1.821255978, a5 = 1.330274429;

    L = Math.abs(X);
    K = 1.0 / (1.0 + 0.2316419 * L);
    w = 1.0 - 1.0 / Math.sqrt(2.0 * Math.PI) * Math.exp(-L * L / 2) * (a1 * K + a2 * K * K + a3 * Math.pow(K, 3) + a4 * Math.pow(K, 4) + a5 * Math.pow(K, 5));

    if (X < 0.0)
    {
      w = 1.0 - w;
    }
    return w;
  }
  /**
   * Method getGreekDelta. How much does the option price change if the stock price is 
   * changing by a small amount (e.g. 1 cent)? <BR>
   * <B>delta Call = <font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>)</font></B><BR>
   * <B>delta Put = <font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>) - 1</font></B><BR>
   * Sample Call: 
   * <PRE>
   * Financials.getGreekDelta(OptionType.CALL_OPTION, // Call Type Option
   *                          60.0,                   // Stock Price
   *                          65.0,                   // Strike Price
   *                          0.25,                   // 1/4 of year
   *                          0.08,                   // 8% risk free
   *                          0.30);                  // Volatilty
   * </PRE>
   * Delta measures the sensitivity of an option's theoretical 
   * value to a change in the price of the underlying asset.  Delta is a very 
   * important number to consider when constructing combination positions.  
   * Call options have positive deltas and put options have negative deltas.  
   * At-the-money options generally have deltas around 50.  Deeper in-the-money 
   * options might have a delta of 80 or higher.  Out-of-the-money options have 
   * deltas as small as 20 or less.  Delta will change as the option becomes 
   * further in or out-of- the money.  When a stock option is deep in the money, 
   * it will begin to trade like the stock - moving dollar for dollar with the 
   * underlying stock, while the far out-of-the-money options don’t move much.<BR>
   * Delta is the primary indicator used when monitoring option risk. Most often, 
   * delta is used as the "hedge ratio". By taking an opposite position in the underlying 
   * instrument equal in size to the option’s delta, we immunize the position against 
   * profit or loss variability due to small movements in the market. This is often 
   * referred to as delta hedging, or creating a delta-neutral portfolio. Delta 
   * hedging is the same process as hedging through duration matching in a fixed 
   * income portfolio. A risk management report could summarize the total equity 
   * exposure by summing up the products of amounts exposed times the delta’s 
   * for each equity, equity index, and any options in the portfolio.<BR>
   * <font face="Times New Roman" size=+1>Example:</font><BR>
   * Using the Black-Scholes European call option model delta is equal to N(d1) or N((ln(Price/K)
   * +(r+Variance/2)*(time to expiration))/squareroot(variance*time to expiration)), 
   * where K is the exercise price, r is the risk free rate for the time to expiration, 
   * and N is the standard normal cumulative distribution function.<BR> 
   * An at the money call has a delta that tends to 0.5 (from above) as the time to expiration 
   * tends to zero. A delta of 0.5 means that a $1.00 increase in the price of the 
   * underlying asset will increase the price of the option by approximately $0.50. 
   * A $1.00 decrease in the price of the asset will decrease the price of the option 
   * by approximately $0.50. Call options have deltas between 0 (0 probability of having value) 
   * and 1(long underlying asset). Put options have deltas between –1 and 0.In-the-money options 
   * have deltas that approach 1 for calls and –1 for puts. Out-of-the-money options have 
   * deltas that approach zero. <BR>
   * <font face="Times New Roman" size=+1>Common Uses:</font><BR>
   * Delta is the primary indicator used when monitoring option risk. Most often, delta is 
   * used as the "hedge ratio". By taking an opposite position in the underlying 
   * instrument equal in size to the option’s delta, we immunize the position against 
   * profit or loss variability due to small movements in the market. This is often 
   * referred to as delta hedging, or creating a delta-neutral portfolio. Delta 
   * hedging is the same process as hedging through duration matching in a fixed income portfolio. 
   * A risk management report could summarize the total equity exposure by summing up the products
   * of amounts exposed times the delta’s for each equity, equity index, and any options in the
   * portfolio.
   * @see Financials#getGreekDelta(OptionType, double, double, double, double, double, double)
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Delta
   */
  public static double getGreekDelta( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double volatility )
  {
    double rc = getGreekDelta(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, riskFreeRate, volatility);
    return rc;
  }
  /**
   * Method getGreekDelta. How much does the option price change if the stock price is 
   * changing by a small amount (e.g. 1 cent)? <BR>
   * <B>delta Call = <font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>)</font></B><BR>
   * <B>delta Put = <font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>) - 1</font></B><BR>
   * Sample Call: 
   * <PRE>
   * Financials.getGreekDelta(OptionType.CALL_OPTION, // Call Type Option
   *                          60.0,                   // Stock Price
   *                          65.0,                   // Strike Price
   *                          0.25,                   // 1/4 of year
   *                          0.08,                   // 8% risk free
   *                          0.08,                   // 8% risk free
   *                          0.30);                  // Volatilty
   * </PRE>
   * Delta measures the sensitivity of an option's theoretical 
   * value to a change in the price of the underlying asset.  Delta is a very 
   * important number to consider when constructing combination positions.  
   * Call options have positive deltas and put options have negative deltas.  
   * At-the-money options generally have deltas around 50.  Deeper in-the-money 
   * options might have a delta of 80 or higher.  Out-of-the-money options have 
   * deltas as small as 20 or less.  Delta will change as the option becomes 
   * further in or out-of- the money.  When a stock option is deep in the money, 
   * it will begin to trade like the stock - moving dollar for dollar with the 
   * underlying stock, while the far out-of-the-money options don’t move much.<BR>
   * Delta is the primary indicator used when monitoring option risk. Most often, 
   * delta is used as the "hedge ratio". By taking an opposite position in the underlying 
   * instrument equal in size to the option’s delta, we immunize the position against 
   * profit or loss variability due to small movements in the market. This is often 
   * referred to as delta hedging, or creating a delta-neutral portfolio. Delta 
   * hedging is the same process as hedging through duration matching in a fixed 
   * income portfolio. A risk management report could summarize the total equity 
   * exposure by summing up the products of amounts exposed times the delta’s 
   * for each equity, equity index, and any options in the portfolio.<BR>
   * <font face="Times New Roman" size=+1>Example:</font><BR>
   * Using the Black-Scholes European call option model delta is equal to N(d1) or N((ln(Price/K)
   * +(r+Variance/2)*(time to expiration))/squareroot(variance*time to expiration)), 
   * where K is the exercise price, r is the risk free rate for the time to expiration, 
   * and N is the standard normal cumulative distribution function.<BR> 
   * An at the money call has a delta that tends to 0.5 (from above) as the time to expiration 
   * tends to zero. A delta of 0.5 means that a $1.00 increase in the price of the 
   * underlying asset will increase the price of the option by approximately $0.50. 
   * A $1.00 decrease in the price of the asset will decrease the price of the option 
   * by approximately $0.50. Call options have deltas between 0 (0 probability of having value) 
   * and 1(long underlying asset). Put options have deltas between –1 and 0.In-the-money options 
   * have deltas that approach 1 for calls and –1 for puts. Out-of-the-money options have 
   * deltas that approach zero. <BR>
   * <font face="Times New Roman" size=+1>Common Uses:</font><BR>
   * Delta is the primary indicator used when monitoring option risk. Most often, delta is 
   * used as the "hedge ratio". By taking an opposite position in the underlying 
   * instrument equal in size to the option’s delta, we immunize the position against 
   * profit or loss variability due to small movements in the market. This is often 
   * referred to as delta hedging, or creating a delta-neutral portfolio. Delta 
   * hedging is the same process as hedging through duration matching in a fixed income portfolio. 
   * A risk management report could summarize the total equity exposure by summing up the products
   * of amounts exposed times the delta’s for each equity, equity index, and any options in the
   * portfolio.
   * @see Financials#getGreekDelta(OptionType, double, double, double, double, double)
   * @see Financials#roundFinancialValue(double) - Used for rounding value
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param b - double cost percentage above the Risk Free Rate 
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Delta
   */
  public static double getGreekDelta( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double b,
                                     double volatility )
  {
    double d1;
    double rawDelta = 0.0;

    d1 = (Math.log(stockPrice / strikePrice) + (b + volatility * volatility / 2.0) * yearsToMaturity) / (volatility * Math.sqrt(yearsToMaturity));

    if (OptionType.CALL_OPTION.equals(flag))
    {
      rawDelta = Math.exp((b - riskFreeRate) * yearsToMaturity) * cummulativeNormalDist(d1);
    }
    else
    { // OptionType.PUT_OPTION.equals(flag)
      rawDelta = Math.exp((b - riskFreeRate) * yearsToMaturity) * (cummulativeNormalDist(d1) - 1);
    }
    rawDelta = roundFinancialValue(rawDelta);

    return rawDelta;
  }
  /**
   * Method roundFinancialPercent.  Rounds a Values, Percentage
   * @param financialValue - double
   * @param PERCENT_ROUND - the percentage
   * @return
   */
  private static double roundFinancialPercent( double financialValue, final double PERCENT_ROUND )
  {
    financialValue = Math.round(financialValue * ROUNDER_VALUE) / PERCENT_ROUND;
    return financialValue;
  }
  /**
   * Method roundFinancialValue.  Rounds a Value by the perscriptive ROUNDER_VALUE
   * @param financialValue - double the value to round.
   * @return
   */
  private static double roundFinancialValue( double financialValue )
  {
    financialValue = Math.round(financialValue * ROUNDER_VALUE) / ROUNDER_VALUE;
    return financialValue;
  }
  /**
   * Method roundFinancialValue.  Rounds a Value by the perscriptive ROUNDER_VALUE
   * @param financialValue - double, the Value to Round.
   * @param PERCENT_ROUND
   * @return
   */
  private static double roundFinancialValue( double financialValue, final double PERCENT_ROUND )
  {
    financialValue = Math.round(financialValue * PERCENT_ROUND) / ROUNDER_VALUE;
    return financialValue;
  }
  /**
   * Method roundFinancialValue.  Rounds a Value by the perscriptive ROUNDER_VALUE, and the Days in a Year., Based on the Option Type
   * @param financialValue
   * @param optionType
   * @return
   */
  private static double roundFinancialValue( double financialValue, OptionType optionType )
  {
    if (OptionType.CALL_OPTION.equals(optionType))
    {
      financialValue = Math.round(financialValue / DAYS_IN_YEAR * ROUNDER_VALUE) / ROUNDER_VALUE;
    }
    else
    { // OptionType.PUT_OPTION.equals(optionType)
      financialValue = Math.round(financialValue * DAYS_IN_YEAR * ROUNDER_VALUE) / ROUNDER_VALUE;
    }
    return financialValue;
  }

  /**
   * Method getGreekGamma. What is the change in option price if the stock price rises 
   * (e.g. by 1 Dollar) compared to the price change if the stock falls (again by a Dollar)? <BR>
   * <B>gamma = <font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>) / 
   * s<FONT FACE="Symbol">s</FONT><FONT FACE="Symbol">&#214;</FONT>t</font></B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekGamma(60.0,       // Stock Price
   *                          65.0,       // Strike Price
   *                          0.25,       // 1/4 of year
   *                          0.08,       // 8% risk free
   *                          30.0/100);  // Implied Volatilty
   * </PRE>

   * Since Delta is such an important factor, the marketplace is 
   * interested in how Delta changes.  Gamma measures the rate of change in the delta 
   * for each one-point increase in the underlying asset.  It is a valuable tool in 
   * helping you forecast changes in the delta of an option or an overall position.  
   * Gamma is largest for the at-the-money options, and gets progressively lower for 
   * both the in and out-of-the-money options.  Unlike Delta, Gamma is always 
   * positive for both calls and puts. <BR>
   * The gamma of an option is the rate of change of the option’s delta with respect 
   * to the price of the underlying asset. It is the second partial derivative of 
   * the option value with respect to the asset price.<BR>
   * <font face="Times New Roman" size=+1>Example:</font><BR>
   * If gamma has a value of 0.2, this means that a $1.00 increase in the price of 
   * the underlying asset will increase the delta of the option by approximately 0.2. 
   * A $1.00 decrease in the price of the asset will decrease the delta of the 
   * option by approximately 0.2. When gamma is small in absolute terms, delta changes 
   * slowly with changes in the underlying asset price. However, if gamma is large 
   * in absolute terms, delta is highly sensitive to the changes in the underlying 
   * asset price. At-the-money options generally have the largest gamma. 
   * The further an option goes in- or out-ofthe-money, the smaller the gamma 
   * generally becomes. Gamma is always positive for a call option.<BR>
   * <font face="Times New Roman" size=+1>Common Uses:</font><BR>
   * Delta plays the same role in approximating the sensitivity of an option’s 
   * price to changes in the price of the underlying asset as duration does for measuring 
   * the sensitivity of a bond’s price. In both cases, the changes are approximations 
   * which are only accurate for small changes in market prices. In both cases, the 
   * approximation can be improved through the use of second derivatives. For bonds, 
   * the second derivative is called convexity. For an option, the second derivative 
   * is often referred to as gamma. <BR> 
   * If gamma is large, creating a delta-neutral portfolio may not provi de adequate immunization 
   * against asset price changes. Delta hedging can be repeated more frequently or the option
   * position can be made “delta- and gamma-neutral”. This is done by taking a position in the
   * underlying asset and an option on the asset such that the delta and gamma of this portfolio is
   * equal and opposite in sign to the option being hedged. Again, this is essentially the 
   * same process as matching duration and convexity to obtain an immunized fixed-income portfolio.
   * @see Financials#getGreekGamma(double, double, double, double, double, double)
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Gamma
   */
  public static double getGreekGamma( double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double volatility )
  {
    double rc = getGreekGamma(stockPrice, strikePrice, yearsToMaturity, riskFreeRate, riskFreeRate, volatility);
    return rc;
  }
  /**
   * Method getGreekGamma. What is the change in option price if the stock price rises 
   * (e.g. by 1 Dollar) compared to the price change if the stock falls (again by a Dollar)? <BR>
   * <B>gamma = <font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>) / 
   * s<FONT FACE="Symbol">s</FONT><FONT FACE="Symbol">&#214;</FONT>t</font></B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekGamma(60.0,       // Stock Price
   *                          65.0,       // Strike Price
   *                          0.25,       // 1/4 of year
   *                          0.08,       // 8% risk free
   *                          30.0/100);  // Implied Volatilty
   * </PRE>
   * Since Delta is such an important factor, the marketplace is 
   * interested in how Delta changes.  Gamma measures the rate of change in the delta 
   * for each one-point increase in the underlying asset.  It is a valuable tool in 
   * helping you forecast changes in the delta of an option or an overall position.  
   * Gamma is largest for the at-the-money options, and gets progressively lower for 
   * both the in and out-of-the-money options.  Unlike Delta, Gamma is always 
   * positive for both calls and puts. <BR>
   * The gamma of an option is the rate of change of the option’s delta with respect 
   * to the price of the underlying asset. It is the second partial derivative of 
   * the option value with respect to the asset price.<BR>
   * <font face="Times New Roman" size=+1>Example:</font><BR>
   * If gamma has a value of 0.2, this means that a $1.00 increase in the price of 
   * the underlying asset will increase the delta of the option by approximately 0.2. 
   * A $1.00 decrease in the price of the asset will decrease the delta of the 
   * option by approximately 0.2. When gamma is small in absolute terms, delta changes 
   * slowly with changes in the underlying asset price. However, if gamma is large 
   * in absolute terms, delta is highly sensitive to the changes in the underlying 
   * asset price. At-the-money options generally have the largest gamma. 
   * The further an option goes in- or out-ofthe-money, the smaller the gamma 
   * generally becomes. Gamma is always positive for a call option.<BR>
   * <font face="Times New Roman" size=+1>Common Uses:</font><BR>
   * Delta plays the same role in approximating the sensitivity of an option’s 
   * price to changes in the price of the underlying asset as duration does for measuring 
   * the sensitivity of a bond’s price. In both cases, the changes are approximations 
   * which are only accurate for small changes in market prices. In both cases, the 
   * approximation can be improved through the use of second derivatives. For bonds, 
   * the second derivative is called convexity. For an option, the second derivative 
   * is often referred to as gamma. <BR> 
   * If gamma is large, creating a delta-neutral portfolio may not provi de adequate immunization 
   * against asset price changes. Delta hedging can be repeated more frequently or the option
   * position can be made “delta- and gamma-neutral”. This is done by taking a position in the
   * underlying asset and an option on the asset such that the delta and gamma of this portfolio is
   * equal and opposite in sign to the option being hedged. Again, this is essentially the 
   * same process as matching duration and convexity to obtain an immunized fixed-income portfolio.
   * @see Financials#getGreekGamma(double, double, double, double, double)
   * @see Financials#roundFinancialValue(double)
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param b - double cost percentage above the Risk Free Rate 
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Gamma
   */
  public static double getGreekGamma( double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double b, double volatility )
  {
    double d1;
    d1 = (Math.log(stockPrice / strikePrice) + (b + volatility * volatility / 2) * yearsToMaturity) / (volatility * Math.sqrt(yearsToMaturity));
    double rawGamma = Financials.normalDist(d1) * Math.exp((b - riskFreeRate) * yearsToMaturity) / (stockPrice * volatility * Math.sqrt(yearsToMaturity));
    rawGamma = roundFinancialValue(rawGamma);
    return rawGamma;
  }
  /**
   * Method getGreekVega. How much does the option price change if the volatility of the stock is 
   * changing by a small amount (e.g. 0.5 %) per year? <BR>
   * <B>vega = s<font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>)<FONT FACE="Symbol">&#214;</FONT>t</font> </B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekVega(60.0,       // Stock Price
   *                         65.0,       // Strike Price
   *                         0.25,       // 1/4 of year
   *                         0.08,       // 8% risk free
   *                         30.0/100);  // Implied Volatilty
   * </PRE>
   * Many people confuse Vega and volatility.  
   * Volatility measures fluctuations in the asset itself.  Vega measures the 
   * sensitivity of the price of an option to changes in volatility.  
   * Changes in volatility affect calls and puts and in a similar way.  
   * An increase in volatility will increase the prices of all the options 
   * in an asset, and visa versa.  However, each individual option has its 
   * own Vega and will react differently.  The impact of volatility changes 
   * is greater for at-the-money options than the in or out-of-the-money options.  
   * While Vega affects calls and puts similarly, it seems to affect calls 
   * more than puts.  Perhaps because of the anticipation of market growth 
   * over time, this effect becomes more pronounced for longer-term options, 
   * especially LEAPS. 
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Vega
   */
  public static double getGreekVega( double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double volatility )
  {
    double rc = getGreekVega(stockPrice, strikePrice, yearsToMaturity, riskFreeRate, riskFreeRate, volatility);
    return rc;
  }
  /**
   * Method getGreekVega. How much does the option price change if the volatility of the stock is 
   * changing by a small amount (e.g. 0.5 %) per year? <BR>
   * <B>vega = s<font face="Times New Roman">&#934;(<i>d</i><sub>1</sub>)<FONT FACE="Symbol">&#214;</FONT>t</font> </B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekVega(60.0,       // Stock Price
   *                         65.0,       // Strike Price
   *                         0.25,       // 1/4 of year
   *                         0.08,       // 8% risk free
   *                         0.08,       // 8% risk free
   *                         30.0/100);  // Implied Volatilty
   * </PRE>
   * Many people confuse Vega and volatility.  
   * Volatility measures fluctuations in the asset itself.  Vega measures the 
   * sensitivity of the price of an option to changes in volatility.  
   * Changes in volatility affect calls and puts and in a similar way.  
   * An increase in volatility will increase the prices of all the options 
   * in an asset, and visa versa.  However, each individual option has its 
   * own Vega and will react differently.  The impact of volatility changes 
   * is greater for at-the-money options than the in or out-of-the-money options.  
   * While Vega affects calls and puts similarly, it seems to affect calls 
   * more than puts.  Perhaps because of the anticipation of market growth 
   * over time, this effect becomes more pronounced for longer-term options, 
   * especially LEAPS. 
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param b - double cost percentage above the Risk Free Rate 
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Vega
   */
  public static double getGreekVega( double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double b, double volatility )
  {
    double d1 = 0.0;
    double rawVega = 0.0;
    final double PERCENT_ROUND = 100.0;

    d1 = (Math.log(stockPrice / strikePrice) + (b + volatility * volatility / 2.0) * yearsToMaturity) / (volatility * Math.sqrt(yearsToMaturity));

    rawVega = stockPrice * Math.exp((b - riskFreeRate) * yearsToMaturity) * Financials.normalDist(d1) * Math.sqrt(yearsToMaturity);
    rawVega = roundFinancialValue(rawVega, PERCENT_ROUND);
    return rawVega;

  }
  /**
   * Method getGreekTheta. How much does the option price change if the expiration 
   * date of the option is changing by a small amount (e.g. 1 day)?  <BR>
   * <B>theta call= <font face="Times New Roman">- ( s&#934;(<i>d</i><sub>1</sub>)<FONT FACE="Symbol">s</FONT> / 2<FONT FACE="Symbol">&#214;</FONT>t) - <i>rxe<sup>-rt</sup></i>&#934;(<i>d</i><sub>2</sub>)</font></B><BR>
   * <B>theta Put= <font face="Times New Roman">-( s&#934;(<i>d</i><sub>1</sub>)<FONT FACE="Symbol">s</FONT> / 2<FONT FACE="Symbol">&#214;</FONT>t) + <i>rxe<sup>-rt</sup></i>&#934;(-<i>d</i><sub>2</sub>)</font></B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekTheta( OptionType.CALL_OPTION,
   *                           60.0,       // Stock Price
   *                           65.0,       // Strike Price
   *                           0.25,       // 1/4 of year
   *                           0.08,       // 8% risk free
   *                           30.0/100);  // Implied Volatilty
   * </PRE>
   * 
   * Theta is a measure of the time decay of an option.  
   * It is the dollar amount that an option will lose each day.  For at-the-money 
   * options, Theta increases as an option approaches the expiration date.  For in 
   * and out-of-the-money options, theta decreases as an option approaches 
   * expiration.  Theta is one of the most important concepts for a beginning 
   * option trader to understand, because it explains the effect of time on the 
   * premium of the options that have been purchased or sold.  The further out in 
   * time you go, the smaller the time decay will be for an option.  If you want 
   * to own an option, it is advantageous to purchase longer-term contracts.  
   * If you want a strategy that profits from time decay, then you will want to 
   * be short the shorter-term options, so that the loss in value due to time 
   * happens quickly. 
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Theta
   */
  public static double getGreekTheta( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double volatility )
  {
    double rc = getGreekTheta(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, riskFreeRate, volatility);
    return rc;
  }
  /**
   * Method getGreekTheta.  How much does the option price change if the expiration 
   * date of the option is changing by a small amount (e.g. 1 day)?  <BR>
   * <B>theta call= <font face="Times New Roman">- ( s&#934;(<i>d</i><sub>1</sub>)<FONT FACE="Symbol">s</FONT> / 2<FONT FACE="Symbol">&#214;</FONT>t) - <i>rxe<sup>-rt</sup></i>&#934;(<i>d</i><sub>2</sub>)</font></B><BR>
   * <B>theta Put= <font face="Times New Roman">-( s&#934;(<i>d</i><sub>1</sub>)<FONT FACE="Symbol">s</FONT> / 2<FONT FACE="Symbol">&#214;</FONT>t) + <i>rxe<sup>-rt</sup></i>&#934;(-<i>d</i><sub>2</sub>)</font></B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekTheta( OptionType.CALL_OPTION,
   *                           60.0,       // Stock Price
   *                           65.0,       // Strike Price
   *                           0.25,       // 1/4 of year
   *                           0.08,       // 8% risk free
   *                           0.08,       // 8% risk free
   *                           30.0/100);  // Implied Volatilty
   * </PRE>
   * 
   * Theta is a measure of the time decay of an option.  
   * It is the dollar amount that an option will lose each day.  For at-the-money 
   * options, Theta increases as an option approaches the expiration date.  For in 
   * and out-of-the-money options, theta decreases as an option approaches 
   * expiration.  Theta is one of the most important concepts for a beginning 
   * option trader to understand, because it explains the effect of time on the 
   * premium of the options that have been purchased or sold.  The further out in 
   * time you go, the smaller the time decay will be for an option.  If you want 
   * to own an option, it is advantageous to purchase longer-term contracts.  
   * If you want a strategy that profits from time decay, then you will want to 
   * be short the shorter-term options, so that the loss in value due to time 
   * happens quickly. 
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param b - double cost percentage above the Risk Free Rate 
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Theta
   */
  public static double getGreekTheta( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double b,
                                     double volatility )
  {
    double d1, d2;
    double rawTheta = 0.0;

    d1 = (Math.log(stockPrice / strikePrice) + (b + volatility * volatility / 2) * yearsToMaturity) / (volatility * Math.sqrt(yearsToMaturity));
    d2 = d1 - volatility * Math.sqrt(yearsToMaturity);

    if (OptionType.CALL_OPTION.equals(flag))
    {
      rawTheta = -stockPrice * Math.exp((b - riskFreeRate) * yearsToMaturity) * Financials.normalDist(d1) * volatility / (2 * Math.sqrt(yearsToMaturity))
                 - (b - riskFreeRate) * stockPrice * Math.exp((b - riskFreeRate) * yearsToMaturity) * cummulativeNormalDist(d1) - riskFreeRate * strikePrice
                 * Math.exp(-riskFreeRate * yearsToMaturity) * cummulativeNormalDist(d2);
    }
    else
    { // OptionType.PUT_OPTION.equals(flag)
      rawTheta = -stockPrice * Math.exp((b - riskFreeRate) * yearsToMaturity) * Financials.normalDist(d1) * volatility / (2 * Math.sqrt(yearsToMaturity))
                 + (b - riskFreeRate) * stockPrice * Math.exp((b - riskFreeRate) * yearsToMaturity) * cummulativeNormalDist(-d1) + riskFreeRate * strikePrice
                 * Math.exp(-riskFreeRate * yearsToMaturity) * cummulativeNormalDist(-d2);
    }
    rawTheta = roundFinancialValue(rawTheta, flag);

    return rawTheta;
  }

  /**
   * Method getGreekRho. How much does the option price change if the risk-free 
   * interest rate is changing by a small amount (e.g. 0.1 %)? <BR>
   * <B>rho call= <font face="Times New Roman"> <i>xte<sup>-rt</sup></i>&#934;(<i>d</i><sub>2</sub>)</font></B><BR>
   * <B>rho put= <font face="Times New Roman"> - <i>xte<sup>-rt</sup></i>&#934;(-<i>d</i><sub>2</sub>)</font></B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekRho( OptionType.CALL_OPTION,
   *                         60.0,       // Stock Price
   *                         65.0,       // Strike Price
   *                         0.25,       // 1/4 of year
   *                         0.08,       // 8% risk free
   *                         30.0/100);  // Implied Volatilty
   * </PRE>
   * Rho for the generalized Black-Scholes formula, 
   * Does not work for options on futures.<BR>
   * The rho of an option measures the change in an option price with respect to the 
   * domestic riskfree interest rate.
   * <font face="Times New Roman" size=+1>Example:</font><BR>
   * If rho is equal to 0.5, this means that a 1 percent absolute increase 
   * (e.g., from 4% to 5%) in the risk-free interest rate will increase the price of 
   * the option by approximately $0.50. A 1 percent decrease in the risk-free interest 
   * rate will decrease the price of the option by approximately $0.50. Rho is always 
   * positive for European calls and always negative for European puts. Therefore, as 
   * interest rates increase, call option values will rise and put option values will fall. 
   * <font face="Times New Roman" size=+1>Common Uses</font><BR>
   * Similar to theta, rho is not commonly used as a hedge parameter. However, it is a valuable
   * statistic because it shows how sensitive an option is to changes in interest rates. 
   * It can be quite critical in pricing products which contain options 
   * (e.g. Equity Indexed Annuities), to understand how product margins need to change as 
   * interest rates move up and down. The derivative of price with respect to the strike 
   * price (Eta) has not been mentioned above. Also, the derivative of price with respect 
   * to carry costs is sometimes referred to as “rho-b”, Many of these derivatives have 
   * closed form formulas, depending on the underlying distribution of market returns 
   * assumed in the option valuation formula.
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Rho
   */
  public static double getGreekRho( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double volatility )
  {
    double rc = getGreekRho(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, riskFreeRate, volatility);
    return rc;
  }
  /**
   * Method getGreekRho. How much does the option price change if the risk-free 
   * interest rate is changing by a small amount (e.g. 0.1 %)? <BR>
   * <B>rho call= <font face="Times New Roman"> <i>xte<sup>-rt</sup></i>&#934;(<i>d</i><sub>2</sub>)</font></B><BR>
   * <B>rho put= <font face="Times New Roman"> - <i>xte<sup>-rt</sup></i>&#934;(-<i>d</i><sub>2</sub>)</font></B><BR>
   * Example: 
   * <PRE>
   * Financials.getGreekRho( OptionType.CALL_OPTION,
   *                         60.0,       // Stock Price
   *                         65.0,       // Strike Price
   *                         0.25,       // 1/4 of year
   *                         0.08,       // 8% risk free
   *                         0.08,       // 8% risk free
   *                         30.0/100);  // Implied Volatilty
   * </PRE>
   * Rho for the generalized Black-Scholes formula, 
   * Does not work for options on futures.<BR>
   * The rho of an option measures the change in an option price with respect to the 
   * domestic riskfree interest rate.
   * <font face="Times New Roman" size=+1>Example:</font><BR>
   * If rho is equal to 0.5, this means that a 1 percent absolute increase 
   * (e.g., from 4% to 5%) in the risk-free interest rate will increase the price of 
   * the option by approximately $0.50. A 1 percent decrease in the risk-free interest 
   * rate will decrease the price of the option by approximately $0.50. Rho is always 
   * positive for European calls and always negative for European puts. Therefore, as 
   * interest rates increase, call option values will rise and put option values will fall. 
   * <font face="Times New Roman" size=+1>Common Uses</font><BR>
   * Similar to theta, rho is not commonly used as a hedge parameter. However, it is a valuable
   * statistic because it shows how sensitive an option is to changes in interest rates. 
   * It can be quite critical in pricing products which contain options 
   * (e.g. Equity Indexed Annuities), to understand how product margins need to change as 
   * interest rates move up and down. The derivative of price with respect to the strike 
   * price (Eta) has not been mentioned above. Also, the derivative of price with respect 
   * to carry costs is sometimes referred to as “rho-b”, Many of these derivatives have 
   * closed form formulas, depending on the underlying distribution of market returns 
   * assumed in the option valuation formula.
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice - double, the Stock Price
   * @param strikePrice - double, the Strike Price of the Stock
   * @param yearsToMaturity - double, years to maturity.
   * @param riskFreeRate - double, the Risk Free Rate
   * @param b - double cost percentage above the Risk Free Rate 
   * @param volatility - double, the volatility of the Underlying Stock
   * @return double - Greek Rho
   */
  public static double getGreekRho( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double b,
                                   double volatility )
  {
    double d2 = 0.0;
    double rawRho = -1.0;
    final double PERCENT_ROUND = 100.0;

    d2 = (Math.log(stockPrice / strikePrice) + (b - volatility * volatility / 2) * yearsToMaturity) / (volatility * Math.sqrt(yearsToMaturity));

    if (OptionType.CALL_OPTION.equals(flag))
    {
      rawRho = yearsToMaturity * strikePrice * Math.exp(-riskFreeRate * yearsToMaturity) * cummulativeNormalDist(d2);
    }
    else
    { // OptionType.PUT_OPTION.equals(flag)
      rawRho = -yearsToMaturity * yearsToMaturity * Math.exp(-riskFreeRate * yearsToMaturity) * cummulativeNormalDist(-d2);
    }
    rawRho = roundFinancialValue(rawRho, PERCENT_ROUND);
    return rawRho;
  }
  /**
   * Method getBlackScholesGeneral. Generalized Black Scholes formula, returns the Option Value.<BR>
   * <font face="Times New Roman" size=+1>The Black-Scholes Formula</font><BR>
   * The Black-Scholes formula was the first widely-used model for option pricing. 
   * This formula can be used to calculate a theoretical value for an option using current 
   * stock prices, expected dividends, the option's strike price, expected interest rates, 
   * time to expiration and expected stock volatility. While the Black-Scholes model does not 
   * perfectly describe real-world options markets, it is still often used in the valuation and 
   * trading of options. <BR>
   * Example: 
   * <PRE>
   * Financials.getBlackScholesGeneral( OptionType.CALL_OPTION,
   *                                    60.0,       // Stock Price
   *                                    65.0,       // Strike Price
   *                                    0.25,       // 1/4 of year
   *                                    0.08,       // 8% risk free
   *                                    30.0/100);  // Implied Volatilty
   * </PRE>
   * 
   * @param CallPutFlag Financial.CALL_FLAG for call and Financial.PUT_FLAG for put option.
   * @param stockPrice Asset price > 0.0.
   * @param strikePrice Strike price >= 0.0
   * @param yearsToMaturity years to matury of option >0.0
   * @param riskFreeRate risk-free rate >0.0 in percent >=0.0
   * @param volatility market price of option >0.0
   * @return double
   */
  public static double getBlackScholesGeneral( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate,
                                              double volatility )
  {
    double rc = getBlackScholesGeneral(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, riskFreeRate, volatility);
    return rc;
  }
  /**
   * Method getBlackScholesGeneral. Generalized Black Scholes formula, returns the Option Value.<BR>
   * <font face="Times New Roman" size=+1>The Black-Scholes Formula</font><BR>
   * The Black-Scholes formula was the first widely-used model for option pricing. 
   * This formula can be used to calculate a theoretical value for an option using current 
   * stock prices, expected dividends, the option's strike price, expected interest rates, 
   * time to expiration and expected stock volatility. While the Black-Scholes model does not 
   * perfectly describe real-world options markets, it is still often used in the valuation and 
   * trading of options. <BR>
   * Example: 
   * <PRE>
   * Financials.getBlackScholesGeneral( OptionType.CALL_OPTION,
   *                                    60.0,       // Stock Price
   *                                    65.0,       // Strike Price
   *                                    0.25,       // 1/4 of year
   *                                    0.08,       // 8% risk free
   *                                    0.08,       // 8% risk free
   *                                    30.0/100);  // Implied Volatilty
   * </PRE>
   * 
   * @param CallPutFlag Financial.CALL_FLAG for call and Financial.PUT_FLAG for put option.
   * @param stockPrice Asset price > 0.0.
   * @param strikePrice Strike price >= 0.0
   * @param yearsToMaturity years to matury of option >0.0
   * @param riskFreeRate risk-free rate >0.0 in percent >=0.0
   * @param b cost of carry underlying asset can be positive and negative
   * @param volatility market price of option >0.0
   * @return double
   */
  public static double getBlackScholesGeneral( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double b,
                                              double volatility )
  {
    double d1, d2;
    double rawOptionValue = 0.0;

    d1 = (Math.log(stockPrice / strikePrice) + (b + volatility * volatility / 2) * yearsToMaturity) / (volatility * Math.sqrt(yearsToMaturity));
    d2 = d1 - volatility * Math.sqrt(yearsToMaturity);

    if (OptionType.CALL_OPTION.equals(flag))
    { // OptionType.CALL_OPTION
      rawOptionValue = stockPrice * Math.exp((b - riskFreeRate) * yearsToMaturity) * cummulativeNormalDist(d1) - strikePrice
                       * Math.exp(-riskFreeRate * yearsToMaturity) * cummulativeNormalDist(d2);
    }
    else
    { // OptionType.PUT_OPTION
      rawOptionValue = strikePrice * Math.exp(-riskFreeRate * yearsToMaturity) * cummulativeNormalDist(-d2) - stockPrice
                       * Math.exp((b - riskFreeRate) * yearsToMaturity) * cummulativeNormalDist(-d1);
    }
    rawOptionValue = roundFinancialValue(rawOptionValue);
    return rawOptionValue;
  }
  /**
   * Method getImpliedVolatility. Returns Implied Volatility Percent.  Implements the BlackScholes model 
   * for volatility given the basic values for the Black Scholes model, plus the current 
   * market price of a option, the return value of this method, will
   * provide a implied volatility of the stock option.  which can be used to
   * determine the option price. Volatility can be a very 
   * important factor in deciding what kind of options to buy or sell. Volatility shows the 
   * investor the range that a stocks price has fluctuated in a certain period. 
   * The official mathematical value of volatility is denoted as <I>the annualized standard 
   * deviation of a stocks daily price changes.</I><BR><BR>
   * There are two types of Volatility: <B>Statistical Volatility</B> and <B>Implied Volatility</B>.<BR> 
   * <B>Statistical Volatility</B> - a measure of actual asset price changes over a specific period of time.<BR>
   * <B>Implied Volatility</B> - a measure of how much the "market place" expects asset price to move, 
   * for an option price. That is, the volatility that the market itself is implying. <BR><BR>
   * The computation of volatility is a difficult problem for mathematical application. 
   * In the Black-Scholes model, volatility is defined as the annual standard deviation of the stock price. 
   * There is a way in which the strategist can let the market compute the volatility for him. 
   * This is called using the implied volatility - that is, the volatility that the market 
   * itself is implying. This is similar to an efficient market hypothesis. If there is enough 
   * trading interest in an option that is close to the money, that option will generally be fairly priced.
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice Asset price > 0.0, Defalt value = 95.0
   * @param strikePrice Strike price >= 0.0, Defalt value = 100.0
   * @param yearsToMaturity years to matury of option >0.0, Defalt value = 0.5
   * @param riskFreeRate risk-free rate >0.0 in percent >=0.0, Defalt value = 0.10
   * @param cm market price of option >0.0 , Defalt value = 4.82
   * @return double
   */
  public static double getImpliedVolatility( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double cm )
  {
    double rc = getImpliedVolatility(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, riskFreeRate, cm);
    return rc;
  }
  /**
   * Method getImpliedVolatility. Returns Implied Volatility Percent.  Implements the BlackScholes model 
   * for volatility given the basic values for the Black Scholes model, plus the current 
   * market price of a option, the return value of this method, will
   * provide a implied volatility of the stock option.  which can be used to
   * determine the option price. Volatility can be a very 
   * important factor in deciding what kind of options to buy or sell. Volatility shows the 
   * investor the range that a stocks price has fluctuated in a certain period. 
   * The official mathematical value of volatility is denoted as <I>the annualized standard 
   * deviation of a stocks daily price changes.</I><BR><BR>
   * There are two types of Volatility: <B>Statistical Volatility</B> and <B>Implied Volatility</B>.<BR> 
   * <B>Statistical Volatility</B> - a measure of actual asset price changes over a specific period of time.<BR>
   * <B>Implied Volatility</B> - a measure of how much the "market place" expects asset price to move, 
   * for an option price. That is, the volatility that the market itself is implying. <BR><BR>
   * The computation of volatility is a difficult problem for mathematical application. 
   * In the Black-Scholes model, volatility is defined as the annual standard deviation of the stock price. 
   * There is a way in which the strategist can let the market compute the volatility for him. 
   * This is called using the implied volatility - that is, the volatility that the market 
   * itself is implying. This is similar to an efficient market hypothesis. If there is enough 
   * trading interest in an option that is close to the money, that option will generally be fairly priced.
   * @param flag - OptionType, a CALL_OPTION, or PUT_OPTION
   * @param stockPrice Asset price > 0.0, Defalt value = 95.0
   * @param strikePrice Strike price >= 0.0, Defalt value = 100.0
   * @param yearsToMaturity years to matury of option >0.0, Defalt value = 0.5
   * @param riskFreeRate risk-free rate >0.0 in percent >=0.0, Defalt value = 0.10
   * @param b cost of carry underlying asset can be positive and negative, Default = 0.08
   * @param cm market price of option >0.0 , Defalt value = 4.82
   * @return double
   */
  public static double getImpliedVolatility( OptionType flag, double stockPrice, double strikePrice, double yearsToMaturity, double riskFreeRate, double b,
                                            double cm )
  {
    double vLow, vHigh, vi, cLow, cHigh, epsilon;
    double rawVolatility = 0.0;

    int counter;
    counter = 0;
    vLow = 0.005;
    vHigh = 1.0;
    epsilon = 0.0000000001;
    cLow = getBlackScholesGeneral(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, b, vLow);
    cHigh = getBlackScholesGeneral(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, b, vHigh);

    vi = vLow + (cm - cLow) * (vHigh - vLow) / (cHigh - cLow);
    while (Math.abs(cm - getBlackScholesGeneral(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, b, vi)) > epsilon)
    {
      counter = counter + 1;
      if (counter > 100)
        return -1.0;
      if (getBlackScholesGeneral(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, b, vi) < cm)
        vLow = vi;
      else
        vHigh = vi;

      cLow = getBlackScholesGeneral(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, b, vLow);
      cHigh = getBlackScholesGeneral(flag, stockPrice, strikePrice, yearsToMaturity, riskFreeRate, b, vHigh);
      vi = vLow + (cm - cLow) * (vHigh - vLow) / (cHigh - cLow);
    }
    rawVolatility = vi;
    rawVolatility = roundFinancialValue(rawVolatility);
    return rawVolatility;
  }

  /**
   * Method getHistoricVolatility.  Returns Statistical Volatility.  Volatility can be a very 
   * important factor in deciding what kind of options to buy or sell. Volatility shows the 
   * investor the range that a stocks price has fluctuated in a certain period. 
   * The official mathematical value of volatility is denoted as <I>the annualized standard 
   * deviation of a stocks daily price changes.</I><BR><BR>
   * There are two types of Volatility: <B>Statistical Volatility</B> and <B>Implied Volatility</B>.<BR> 
   * <B>Statistical Volatility</B> - a measure of actual asset price changes over a specific period of time.<BR>
   * <B>Implied Volatility</B> - a measure of how much the "market place" expects asset price to move, 
   * for an option price. That is, the volatility that the market itself is implying. <BR><BR>
   * The computation of volatility is a difficult problem for mathematical application. 
   * In the Black-Scholes model, volatility is defined as the annual standard deviation of the stock price. 
   * There is a way in which the strategist can let the market compute the volatility for him. 
   * This is called using the implied volatility - that is, the volatility that the market 
   * itself is implying. This is similar to an efficient market hypothesis. If there is enough 
   * trading interest in an option that is close to the money, that option will generally be fairly priced.
   * @param values - double[]  an array of values.
   * @return double - Historic Volatility
   */
  public static double getHistoricVolatility( double[] values )
  {
    final int days = values.length;
    int j = 0;
    double rc = 0.0;
    double[] returnsOfDay = new double[days];
    double summationOfReturns = 0.0;
    final double PERCENT_ROUND = 100.0;
    final int SQUARED = 2;
    // The first step is to work out the returns. This done for each day by 
    // dividing each day's price by the previous day's price and taking the 
    // natural logarithm of the answer: 
    // Ri = ln( Pi / Pi - 1 )

    for (int i = 1; i < days; i++, j++)
    {
      double current = values[i];
      double previous = values[i - 1];
      returnsOfDay[j] = Math.log(current / previous);
      // Collect the Sum of Ri while we are in the loop
      summationOfReturns += returnsOfDay[j];
    }

    // The next step is to compute the means of the returns. If there are 
    // n days of observations, then the mean return is 
    // meanReturn = 1/n * sum( Ri ) 1 to n 
    if (days > 1)
    {

      double meanOfReturns = 1 / days * summationOfReturns;

      // We are now ready to compute the volatility. This is done by 
      // multiplying by 100 to get the percentage, of the annualised standard 
      // deviation of the returns: 
      // volatility = 100 * sqrt( daysIn year/ n * sum( ( Ri - meanReturn ) ^2 ))
      double summationOfReturnLessMeanSq = 0.0;
      for (int i = 0; i < returnsOfDay.length; i++)
      {
        summationOfReturnLessMeanSq += Math.pow((returnsOfDay[i] - meanOfReturns), SQUARED);
      }
      double annulizeDays = DAYS_IN_YEAR / days;
      double theBigValue = summationOfReturnLessMeanSq * annulizeDays;
      double volatility = Math.sqrt(theBigValue);
      volatility = roundFinancialPercent(volatility, PERCENT_ROUND);
      rc = volatility;
    }

    return rc;
  }
  /**
   * Method main.  Test Driver of the Financial Values
   * @param args
   */
  public static void main( String[] args )
  {
    double stockPrice = 60.0;
    double strikePrice = 65.0;
    double riskFreeRate = 0.08; // 8%
    double volatility = 0.30; // 30%
    double timeValue = 0.25;
    double optionValue = 5.34;

    double impliedVolatility = Financials.getImpliedVolatility(OptionType.CALL_OPTION, stockPrice, strikePrice, timeValue, riskFreeRate, optionValue);
    System.out.println("Implied Volatility = " + (impliedVolatility * 100.0) + "%");
    System.out.println("Option Value  = "
                       + Financials.getBlackScholesGeneral(OptionType.CALL_OPTION, stockPrice, strikePrice, timeValue, riskFreeRate, impliedVolatility));
    System.out.println("---------------------------------------------------");
    System.out.println("Volatility    = " + (volatility * 100.0) + "%");
    System.out.println("Option Value  = "
                       + Financials.getBlackScholesGeneral(OptionType.CALL_OPTION, stockPrice, strikePrice, timeValue, riskFreeRate, volatility));
    System.out.println("Greek Delta   = " + Financials.getGreekDelta(OptionType.CALL_OPTION, stockPrice, strikePrice, timeValue, riskFreeRate, volatility));
    System.out.println("Greek Gamma   = " + Financials.getGreekGamma(stockPrice, strikePrice, timeValue, riskFreeRate, volatility));
    System.out.println("Greek Vega    = " + Financials.getGreekVega(stockPrice, strikePrice, timeValue, riskFreeRate, volatility) + "( for 1% point )");
    System.out.println("Greek Theta   = " + Financials.getGreekTheta(OptionType.CALL_OPTION, stockPrice, strikePrice, timeValue, riskFreeRate, volatility)
                       + "( for 1 day )");
    System.out.println("Greek Rho     = " + Financials.getGreekRho(OptionType.CALL_OPTION, stockPrice, strikePrice, timeValue, riskFreeRate, volatility)
                       + "( for 1% point )");

  }
}