import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Stock_Test {
	
	public static String COMMON = "Common";
	public static String PREFERRED = "Preferred";
	public static int i =1;
	
	public static void main(String[] args) {
		
		Map<String, String> stocksHeader = null;
		Map<String, String> stockRecords = null;
		Map<String, String> stockTransaction = null;
		Map<String, String> stockTransactionHeaders = null;
		Map<String, Double> stocksValue = null;
		BufferedReader br = null;
		String input = null;
		
		
		System.out.println("*********************************");
    	System.out.println("1. Dividend Yield");
    	System.out.println("2. P/E Ratio");
    	System.out.println("3. Geometric Mean");    	
    	System.out.println("4. Stock Transations");
    	System.out.println("Enter q for Exit");
    	System.out.println("*********************************");
    	
    	stocksHeader = new HashMap<String, String>();
		stockRecords = new HashMap<String, String>();
		stockTransaction = new HashMap<String, String>();
		stockTransactionHeaders = new HashMap<String, String>();
		stocksHeader.put("stocks_Header", "Stock_Symbol,Type,Last_Dividend,Fixed_Dividend,Per_Value");
		stockRecords.put("TEA", "TEA,Common,0,-,100");
		stockRecords.put("POP","POP,Common,8,-,100");
		stockRecords.put("ALE","ALE,Common,23,-,60");
		stockRecords.put("GIN","GIN,Preferred,8,0.02,100");
		stockRecords.put("JOE","JOE,Common,13,-,250");
		
		try{
			while(true)
	    	{
		        try {
		        	
		        	System.out.println("Please select the Option(1-4) / Exit - q/Q : ");
		        	
		            br = new BufferedReader(new InputStreamReader(System.in));
		            input = br.readLine();
		            if(input.equals("1"))
		            {	 
		            	stocksValue = new HashMap<String, Double>();
		            	System.out.println("1. Dividend Yield");
		            	System.out.println("Please enter price value for the Stocks");
		            	for(Entry<String, String> key : stockRecords.entrySet())
		            	{
		            		System.out.print(key.getKey()+" : ");
		            		String value = br.readLine();
		            		
		            		if(value.equalsIgnoreCase("") || value==null || isDouble(value))
		            		{
		            			value = "0";
		            		}
		            		stocksValue.put(key.getKey(), Double.parseDouble(value));	            		
		            	}
		            	dividend_Yield(input,stockRecords,stocksValue, stockTransaction);
		            	i++;
		            }
		            else if(input.equals("2"))
		            {
		            	stocksValue = new HashMap<String, Double>();
		            	System.out.println("2. P/E Ratio");
		            	System.out.println("Please enter price value for the Stocks");
		            	for(Entry<String, String> key : stockRecords.entrySet())
		            	{
		            		System.out.print(key.getKey()+" : ");
		            		String value = br.readLine();
		            		if(value.equalsIgnoreCase("") || value==null || isDouble(value))
		            		{
		            			value = "0";
		            		}
		            		
		            		stocksValue.put(key.getKey(), Double.parseDouble(value));
		            	}
		            	dividend_Yield(input,stockRecords,stocksValue,stockTransaction);
		            	i++;
		            }
		            else if(input.equals("3"))
		            {
		            	stocksValue = new HashMap<String, Double>();
		            	System.out.println("3. Geometric Mean");
		            	System.out.println("Please enter price value for the Stocks");
		            	for(Entry<String, String> key : stockRecords.entrySet())
		            	{
		            		System.out.print(key.getKey()+" : ");
		            		String value = br.readLine();
		            		if(value.equalsIgnoreCase("") || value==null || isDouble(value))
		            		{
		            			value = "0";
		            		}
		            		
		            		stocksValue.put(key.getKey(), Double.parseDouble(value));
		            	}
		            	stockTransaction = geometric_Mean(stocksValue,stockTransaction);
		            i++;
		            	
		            }
		            else if(input.equals("4"))
		            {
		            	stockTransactionHeaders.put("Records", "Stock_Symbol	TimeStamp	Quantity	Indicator	Price");	            	
		            	System.out.println("4. Stock Transations");
		            	System.out.println("User given value and calculation will display here:");
		            	for(Entry<String, String> key:stockTransactionHeaders.entrySet())
		            	{
		            		System.out.println(key.getKey()+"  	 "+key.getValue());
		            	}
		            	if(stockTransaction.size() > 0)
		            	{
		            		for(Entry<String, String> key : stockTransaction.entrySet())
			            	{
			            		System.out.println(key.getKey()+" : "+key.getValue());	            		
			            	}	            		
		            	}
		            	else
		            	{
		            		System.out.println("No Transaction Records");
		            	}
		            	
		            }
		            else if(input.equalsIgnoreCase("q"))
		            {
		            	System.exit(0);
		            }
		        } catch (IOException e) {
		        	e.printStackTrace();
		        }finally{
		        	
		        }
	    	}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			stocksHeader = null;
			stockRecords = null;
			stockTransaction = null;
			stockTransactionHeaders = null;
			stocksValue = null;
			br = null;
			input = null;
		}
    	
	}

	
	public static Map<String, String> dividend_Yield(String input,Map<String, String> stockRecords,Map<String, Double> stocksValue,Map<String, String> stockTransaction)
	{
		String[] sValue= null;
		
		String Stock_Symbol = null;
		String Type = null;
		Double Last_Dividend = 0.0;
		Double Fixed_Dividend = 0.0;			
		Double Per_Value = 0.0;
		Double ft = 0.0;
		Date d = null;
		String transaction_Value = null;
		try{
			for(Entry<String, String> key : stockRecords.entrySet())
			{
				sValue= key.getValue().split(",");
				
				Stock_Symbol = sValue[0];
				Type = sValue[1];
				Last_Dividend = sValue[2].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[2]);
				Fixed_Dividend = sValue[3].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[3]);			
				Per_Value = sValue[4].equalsIgnoreCase("-")? 0F : Double.parseDouble(sValue[4]);
				ft = 0.0;
				if(input.equals("1"))
				{
					if(Type.equalsIgnoreCase(COMMON))
					{
						ft = Last_Dividend / stocksValue.get(Stock_Symbol);
					}
					else if(Type.equalsIgnoreCase(PREFERRED))
					{
						ft = (Fixed_Dividend * Per_Value) / stocksValue.get(Stock_Symbol);
					}
				}
				else if(input.equals("2"))
				{			
					if(Type.equalsIgnoreCase(COMMON))
					{
						ft = stocksValue.get(Stock_Symbol) / Last_Dividend;
					}
					else if(Type.equalsIgnoreCase(PREFERRED))
					{
						ft = stocksValue.get(Stock_Symbol) / (Fixed_Dividend * Per_Value);
					}
				}
				ft = (ft.isNaN() || ft.isInfinite()? 0F : ft);
				System.out.println(Stock_Symbol+" : "+ft);			
				d = new Date();
				transaction_Value = "	"+key.getKey()+"	"+new Timestamp(d.getTime())+"	-		-		"+stocksValue.get(Stock_Symbol); 
				stockTransaction.put("Record-"+i+key.getKey(), transaction_Value);						
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			Stock_Symbol = null;
			Type = null;
			Last_Dividend = 0.0;
			Fixed_Dividend = 0.0;			
			Per_Value = 0.0;
			ft = 0.0;
			d = null;
			transaction_Value = null;
		}

				
		return stockTransaction;
		
	}
	
	
	public static Map<String, String> geometric_Mean(Map<String, Double> stocksValue,Map<String, String> stockTransaction)
	{
		double sum = 1.0;
		Date d = null;
		String transaction_Value = null;
		double geoMean = 0.0;
		try{
			for(Entry<String, Double> key : stocksValue.entrySet())
			{
				sum = sum * key.getValue();
				d = new Date();
				transaction_Value = "	"+key.getKey()+"	"+new Timestamp(d.getTime())+"	-		-		"+key.getValue(); 
				stockTransaction.put("Record-"+i+key.getKey(), transaction_Value);
			}
			geoMean = Math.pow(sum, 1.0/stocksValue.size());
			System.out.println("geometric mean of prices for all stocks");
			System.out.println(geoMean);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			sum = 1.0;
			d = null;
			transaction_Value = null;
			geoMean = 0.0;
		}
		
		return stockTransaction;
	}
	
	@SuppressWarnings("unused")
	public static boolean isDouble(String str)
	{
		try{			
			Double f = Double.parseDouble(str);
		}catch (NumberFormatException  e) {
			return true;
		}
		return false;
	}
}




