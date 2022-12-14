package com.example.batchdemo;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FurikaeResult {
	public String kozabango;
	public String price;
	public String result;

//	public FurikaeResult(String kozabango, String price, String result) {
//		this.kozabango = kozabango;
//		this.price = price;
//		this.result = result;
//	}

	public String getKozabango() {
		return kozabango;
	}

	public void setKozabango(String kozabango) {
		this.kozabango = kozabango;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}

class Abc extends FurikaeResult {


	
	public double addprice;

	public double getAddprice() {
		return addprice;
	}

	public void setAddprice(double addprice) {
		this.addprice = addprice;
	}
	
	
}

class Bbc  {


	
	
	public double addprice;

	public String memo;
	
	public double getAddprice() {
		return addprice;
	}

	public void setAddprice(double addprice) {
		this.addprice = addprice;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}


class Matome {
	public String id;
	public String kozabango;
	public String price;
	public String result;
	public Double kingaku;
	
	
	public Double getKingaku() {
		return kingaku;
	}

	public void setKingaku(Double kingaku) {
		this.kingaku = kingaku;
	}

	public Matome() {
		
	}
	
	public Matome(String id, String kozabango, String price, String result) {
		this.id = id;
		this.kozabango = kozabango;
		this.price = price;
		this.result = result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKozabango() {
		return kozabango;
	}

	public void setKozabango(String kozabango) {
		this.kozabango = kozabango;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

public class Test {
    /**
     * ?????????????????????????????????????????????????????????
     * @param d
     * @return
     */
    public static double formatDouble1(double d) {
        return (double)Math.round(d*100)/100;
    }
    
    /**
     * The BigDecimal class provides operations for arithmetic, scale manipulation, rounding, comparison, hashing, and format conversion.
     * @param d
     * @return
     */
    public static double formatDouble2(double d) {
        // ????????????????????????????????????
//        BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);
        // ??????????????????????????????????????????????????????RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        return bg.doubleValue();
    }

    /**
     * NumberFormat is the abstract base class for all number formats. 
     * This class provides the interface for formatting and parsing numbers.
     * @param d
     * @return
     */
    public static String formatDouble3(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // ??????????????????
        nf.setMaximumFractionDigits(2); 
        // ??????????????????????????????????????????RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        return nf.format(d);
    }
    
    /**
     * ???????????????????????????
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers. 
     * @param d
     * @return
     */
    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }
    
    /**
     * ???????????????????????????????????????????????????????????????????????????????????????????????????
     * ????????????????????????System.out.println(String.format("%.2f", d));
     * @param d
     * @return
     */
    public static String formatDouble5(double d) {
        return String.format("%.2f", d);
    }
	private static int getMode(List<String> numList) {
		//???????????????????????????
		int[] numArray = new int[numList.size()];
		// ??????????????????
	    for (int i = 0; i < numList.size(); i++) {
	    	//string?????????int???????????????
	    	numArray[i] = Integer.valueOf(numList.get(i));
	    }
		
		// ???????????????
		Arrays.sort(numArray);

		// ?????????
		int pre_mode, num, max_num;
		int mode;

		// ??????????????????
		// ?????????????????????
		mode = numArray[0];
		// ???????????????????????????
		max_num = 1;

		// ?????????????????????????????????
		pre_mode = numArray[0];
		// ????????????
		num = 1; 

		for (int i = 1; i < numArray.length; ++i) {
			if (pre_mode == numArray[i]) {
				// ??????????????????
				// ???????????????1?????????
				++num;
			} else {
				// ????????????????????????
				if (num > max_num) {
					mode = pre_mode;
					max_num = num;
				}
				// ??????????????????????????????????????????
				pre_mode = numArray[i];
				num = 1;
			}
		}

		// ?????????
		if (num > max_num) {
			mode = pre_mode;
			max_num = num;
		}
		
		return mode;
	}
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		System.out.println("-----test-----");
		Matome testma = new Matome(null, null, null, null);

		
		Map<String, Object> maap = new HashMap();

		maap.put("aina", testma);
		
		
		
		String b = "06";

		int c = Integer.valueOf(b);


		
		// ?????????10?????????
		int[] a = new int[5];

		// ????????????
		a[0] = 4;
		a[1] = 1;
//		a[2] = 1;
		a[3] = 4;
		a[4] = 4;
//		a[5] = 1;
//		a[6] = 1;
//		a[7] = 7;
//		a[8] = 7;
//		a[9] = 55;

		// ???????????????
		Arrays.sort(a);

		// ?????????
		int pre_mode, num, max_num;
		int mode;

		// ??????????????????
		mode = a[0]; // ?????????????????????
		max_num = 1; // ???????????????????????????

		pre_mode = a[0]; // ?????????????????????????????????
		num = 1; // ????????????

		for (int i = 1; i < a.length; ++i) {
			if (pre_mode == a[i]) {
				// ??????????????????
				// ???????????????1?????????
				++num;
			} else {
				// ????????????????????????
				if (num > max_num) {
					mode = pre_mode;
					max_num = num;
				}

				// ??????????????????????????????????????????
				pre_mode = a[i];
				num = 1;
			}
		}

		// ?????????
		if (num > max_num) {
			mode = pre_mode;
			max_num = num;
		}

		
		
		
		String str1 = "a";
		
		switch (str1) {
		case "a":
			System.out.println("print a");
			break;
		case "b":
			System.out.println("print b");
			break;
		}
		
		
		
		
//		// ????????????
//		System.out.println("????????? : " + mode);
//		System.out.println("????????? : " + max_num);

		   List<String> list = new ArrayList<>();
		   list.add("20");
		   list.add("20");
		   list.add("20");
		   list.add("20");

		   
		   list.add("05"); 

		   list.add("19");
		   list.add("19");
		   list.add("19");
		   list.add("19");
		   list.add("19");
		   list.add("19");
		   list.add("19");
		   list.add("19");
	        list.add("01");
	        list.add("05");
	        list.add("13");
	        list.add("19");
	        list.add("19");
	        list.add("01");
	        list.add("01");
	        list.add("01");

	        list.add("02");
	        list.add("02");
	        list.add("02");
	        list.add("02");
	        list.add("02");
		
	      
	        //int mode = getMode(list);
	        
	        System.out.println(String.format("%02d", mode));
	        
	        
	        
	        
	        List<Matome> aa = new ArrayList<Matome>();
	        Matome a1 = new Matome("a11111", "1", "1", "1");
	        Matome a2 = new Matome("a11111", "1", "1", "1");
	        Matome a3 = new Matome("a11111", "1", "1", "1");
	        Matome a4 = new Matome("a11111", "1", "1", "1");
	        Matome a5 = new Matome("a11111", "1", "1", "1");
	        aa.add(a1);
	        aa.add(a2);
	        aa.add(a3);
	        aa.add(a4);
	        aa.add(a5);
	        
	        
	        aa.get(0).setPrice("6666666");
	        
	        System.out.println(aa.get(0).getPrice());
	        
	        
	        List<Matome> bb = new ArrayList<Matome>();

	        
	        bb.addAll(aa);
	        
	        for (Matome matome : bb) {
	        	System.out.println(matome.getId());
	        }
	        
	        
	        
	        List<List<Matome>> xx = devide(bb,2);
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        	
	        
	        Matome cc = new Matome();
	        String temp = cc.getId();
	        
	        System.out.println("temp" + temp);
	        
	        
	        
	        
	        double aa1 = 10;

			Date businessDate = new Date();
	        System.out.println(businessDate);
	        
	        
	        
	        
	        Abc abc = new Abc(); 
	        
	        abc.setAddprice(1.1);
//	        
	        Bbc bbb = new Bbc();
	        
	        
	        
	        
	        String xa = String.valueOf(bbb.getMemo());
	        System.out.println(xa);
	        
	        System.out.println(getCurrentTimeStamp());
	        
	        
	        
	    	String ITEMNAME_CONTRACTEX = "???????????????";
	    	String ITEMNAME_IMPOSED_EX = "?????????????????????????????????????????????????????????";
	    	String ITEMNAME_JFS_99_SC = "JFS-99-SC??????";
	    	String ITEMNAME_99_SC = "???-99-SC??????";
	    	String ITEMNAME_OVERDUE_TAXEX = "?????????(?????????)";
	    	String ITEMNAME_IMPOSED = "????????????????????????????????????????????????";
	    	String ITEMNAME_OVERDUEINTEREST = "????????????";
	        
	        String bmw = "?????????(?????????)";
	        
			if (bmw.equals(ITEMNAME_OVERDUEINTEREST)
    				||
    				bmw.equals(ITEMNAME_OVERDUE_TAXEX)) {
				
				System.out.println("Match----------ok-----------------------");
				
			} else {
				System.out.println("UNMatch----------------NG-----------------");
			}
	        
	        
	        
			List<String> animals = new ArrayList<String>();
			
			animals.add("PMSI01346819");
			animals.add("PMSI02346815");
			animals.add("PMSI01346815");
			animals.add("PMSI01346816");
			animals.add("PMSI01346817");
			
			System.out.println("???????????????:" + animals);
			 
			Collections.sort(animals);
			System.out.println("???????????????:" + animals);
	        
			double ddd = 5000.099991;
			
			
			System.out.println(getRoundingValue(ddd));

//	        List<String> test = new ArrayList<>();
//	        
//	        List<String> testb = new ArrayList<>();
//	        testb.add("b");
//	        
//	        List<String> testc = new ArrayList<>();
//
//	        
//	        test.addAll(testb);
//	        test.addAll(testc);
//	        
//	        for (String a : test) {
//	        	System.out.println(a);
//	        }
//	        
//	        
//	        double f1 = 0.0;
//	        
//	        if (f1 == 0.0000) {
//	        	System.out.println("=0");
//	        }
	        		
	        
	        
	        
//        String targetStr4 = new String("Java.hello.world");
//        Pattern pattern4 = Pattern.compile("\\.+?\\");
//        Matcher matcher4 = pattern4.matcher(targetStr4);
//        
//		System.out.println(Matome.class.getSimpleName());
//		
//		String abc = null;
//		try {
//			abc = "hello";
//			throw new NullPointerException(); 
//			
//		} catch (Exception e) {
//			System.out.println(abc);
//		}
//        String s1 = "Java.hello.world";
//        String regex = "\\.+?";
//        Pattern p = Pattern.compile(regex);
//        Matcher m1 = p.matcher(s1);
//        if (m1.find()) {
//        	// ????????????????????????????????????????????????
//        	System.out.println( m1.group () );
//        }

//        List<FurikaeResult> furikaeList = new ArrayList<>();
//        List<FurikaeResult> furikaeOKList = new ArrayList<>();
//        List<FurikaeResult> furikaeNGList = new ArrayList<>();
//        List<Matome> matomeList = new ArrayList<>();
//        List<Matome> matomeiUpdateList = new ArrayList<>();
//        
//        furikaeList.add(new FurikaeResult("11111111","3800","0"));
//        furikaeList.add(new FurikaeResult("11111112","3800","0"));
//        furikaeList.add(new FurikaeResult("11111113","3800","0"));
//        furikaeList.add(new FurikaeResult("11111144","3800","0"));
//        
//        
//        matomeList.add(new Matome("1", "11111111","3800","-"));
//        matomeList.add(new Matome("2", "11111112","3800","-"));
//        matomeList.add(new Matome("3", "11111113","3800","-"));
//        matomeList.add(new Matome("4", "11111114","3800","-"));
//        matomeList.add(new Matome("5", "11111115","3800","-"));
//        
//        boolean kozabangoCheckFL = false;
//        
//        for (int i=0; i<furikaeList.size(); i++) {
//        	kozabangoCheckFL = false;
//        	for(int j=0; j<matomeList.size();j++) {
//        		if (furikaeList.get(i).getKozabango().equals(matomeList.get(j).getKozabango())) {
//        			matomeiUpdateList.add(new Matome(matomeList.get(j).getId(), matomeList.get(j).getKozabango(), matomeList.get(j).getPrice(),furikaeList.get(i).getResult()));
//        			furikaeOKList.add(new FurikaeResult(furikaeList.get(i).getKozabango(), furikaeList.get(i).getPrice(), furikaeList.get(i).getResult()));
//        			kozabangoCheckFL = true;
//        		}
//        	}
//        	if (kozabangoCheckFL == false) {
//        		furikaeNGList.add(new FurikaeResult(furikaeList.get(i).getKozabango(), furikaeList.get(i).getPrice(), furikaeList.get(i).getResult()));
//        	}
//        }
//        System.out.println("--------------ok----------------");
//        for (FurikaeResult furikaeResult: furikaeOKList) {
//        	
//        	System.out.println(furikaeResult.getKozabango() + ", " + furikaeResult.getPrice() + ", " +  furikaeResult.getResult());
//        }
//        System.out.println("--------------ng----------------");
//        for (FurikaeResult furikaeResult: furikaeNGList) {
//        	
//        	System.out.println(furikaeResult.getKozabango() + ", " + furikaeResult.getPrice() + ", " +  furikaeResult.getResult());
//        }
//        System.out.println("--------------update----------------");
//        for (Matome matome: matomeiUpdateList) {

//        	
//        	System.out.println(matome.getId() + ", " + matome.getKozabango() + ", " + matome.getPrice()  + ", " + matome.getResult());
//        	
//        }

//		ArrayList<String> in = new ArrayList<String>();
//		in.add("20210901");
//		in.add("20210901");
//		in.add("20210901");
//		in.add("20210902");
//		in.add("20210902");
//		in.add("20210902");
//		in.add("20210903");
//		in.add("20210903");
//		in.add("20210903");
//		
//		ArrayList<String> out = new ArrayList<String>();
//		
//		for (int i=0; i<in.size(); i++) {
//			if (i==0) {
//				//new object
//				out.add(in.get(i));
//			}
//
//			if (!out.contains(in.get(i))) {
//				//new object
//				out.add(in.get(i));
//			}
//		}
//		
//		for (int i=0; i<out.size(); i++){
//			System.out.println(out.get(i));
//		}
		Map<String, Object> returnMap = new HashMap();
		
		String c1 = "1";
		String c2 = "2";
		
		
		if (c1 == "1") {
			returnMap.put("msgKbn", "c1-value");
		} else if (c2 == "2") {
			returnMap.put("msgKbn", "c2-value");
		}
		
		System.out.println(returnMap.get("msgKbn"));
		System.out.println("end");
		
		FurikaeResult furikaeResults = new FurikaeResult();
		furikaeResults.setKozabango("1");
		furikaeResults.setPrice("108.5");
		
		Class<? extends FurikaeResult> clazz = furikaeResults.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			String fildName = field.getName();
			byte[] items = fildName.getBytes();
			items[0] = (byte)((char) items[0] - 'a' + 'A');
			String getitem = new String(items);
			System.out.println(getitem);
		}
		
	}

//    public static <T> String getName(SFunction<T, ?> fn) {
//        // ???function?????????????????????
//        Method writeReplaceMethod;
//        try {
//            writeReplaceMethod = fn.getClass().getDeclaredMethod("writeReplace");
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//
//        // ????????????????????????????????????lambda??????
//        boolean isAccessible = writeReplaceMethod.isAccessible();
//        writeReplaceMethod.setAccessible(true);
//        SerializedLambda serializedLambda;
//        try {
//            serializedLambda = (SerializedLambda) writeReplaceMethod.invoke(fn);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//        writeReplaceMethod.setAccessible(isAccessible);
//
//        // ???lambda????????????method???field???class???
//        String fieldName = serializedLambda.getImplMethodName().substring("get".length());
//        fieldName = fieldName.replaceFirst(fieldName.charAt(0) + "", (fieldName.charAt(0) + "").toLowerCase());
//        Field field;
//        try {
//            field = Class.forName(serializedLambda.getImplClass().replace("/", ".")).getDeclaredField(fieldName);
//        } catch (ClassNotFoundException | NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//
//        // ???field????????????????????????????????????????????????
//        Matome tableField = field.getAnnotation(Matome.class);
//        if (tableField != null && tableField.value().length() > 0) {
//            return tableField.value();
//        } else {
//            return fieldName.replaceAll("[A-Z]", "_$0").toLowerCase();
//        }
//    }
	public static <T> List<List<T>> devide(List<T> origin, int size) {
		if (origin == null || origin.isEmpty() || size <= 0) {
			return Collections.emptyList();
		}

		int block = origin.size() / size + (origin.size() % size > 0 ? 1 : 0 );

		List<List<T>> devidedList = new ArrayList<List<T>>(block);
		for (int i = 0; i < block; i ++) {
			int start = i * size;
			int end = Math.min(start + size, origin.size());
			devidedList.add(new ArrayList<T>(origin.subList(start, end)));
		}
		return devidedList;
	}

	private static Timestamp getCurrentTimeStamp () {
		Date date = new Date();
        return new Timestamp(date.getTime());
	}
	
	/**
	 * Double???????????????????????????????????????
	 * 
	 * @param doubleValue Double????????????
	 * @return ??????????????????Double????????????(xxxx.0)
	 */
	private static Double getRoundingValue(Double doubleValue) {
        DecimalFormat df = new DecimalFormat("#.000");
        String formattedDoubleValue = df.format(Math.round(doubleValue));
        
        return Double.valueOf(formattedDoubleValue);
	}
}
