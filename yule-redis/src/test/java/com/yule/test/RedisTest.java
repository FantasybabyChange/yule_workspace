//package com.yule.test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//
//import com.yule.redis.dao.ProvinceDao;
//import com.yule.redis.vo.Province;
//
//public class RedisTest {
//	
//    private ProvinceDao provinceDao;  
//      
//    /** 
//     * 新增 
//     * <br>------------------------------<br> 
//     */  
//    @Test  
//    public void testAddProvince() {  
//        Province province = new Province();  
//        province.setId("Province1");  
//        province.setName("java2000_wl");  
//        boolean result = provinceDao.add(province);  
//        Assert.assertTrue(result);
//    }  
//      
//    /** 
//     * 批量新增 普通方式 
//     * <br>------------------------------<br> 
//     */  
//    @Test  
//    public void testAddProvinces1() {  
//        List<Province> list = new ArrayList<Province>();  
//        for (int i = 10; i < 50000; i++) {  
//            Province province = new Province();  
//            province.setId("Province" + i);  
//            province.setName("java2000_wl" + i);  
//            list.add(province);  
//        }  
//        long begin = System.currentTimeMillis();  
//        for (Province province : list) {  
//        	provinceDao.add(province);  
//        }  
//        System.out.println(System.currentTimeMillis() -  begin);  
//    }  
//      
//    /** 
//     * 批量新增 pipeline方式 
//     * <br>------------------------------<br> 
//     */  
//    @Test  
//    public void testAddProvinces2() {  
//        List<Province> list = new ArrayList<Province>();  
//        for (int i = 10; i < 1500000; i++) {  
//            Province province = new Province();  
//            province.setId("Province" + i);  
//            province.setName("java2000_wl" + i);  
//            list.add(province);  
//        }  
//        long begin = System.currentTimeMillis();  
//        boolean result = provinceDao.add(list);  
//        System.out.println(System.currentTimeMillis() - begin);  
//        Assert.assertTrue(result);  
//    }  
//      
//    /** 
//     * 修改 
//     * <br>------------------------------<br> 
//     */  
//    @Test  
//    public void testUpdate() {  
//        Province province = new Province();  
//        province.setId("province1");  
//        province.setName("new_password");  
//        boolean result = provinceDao.update(province);  
//        Assert.assertTrue(result);  
//    }  
//      
//    /** 
//     * 通过key删除单个 
//     * <br>------------------------------<br> 
//     */  
//    @Test  
//    public void testDelete() {  
//        String key = "province1";  
//        provinceDao.delete(key);  
//    }  
//      
//    /** 
//     * 批量删除 
//     * <br>------------------------------<br> 
//     */  
//    @Test  
//    public void testDeletes() {  
//        List<String> list = new ArrayList<String>();  
//        for (int i = 0; i < 10; i++) {  
//            list.add("province" + i);  
//        }  
//        provinceDao.delete(list);  
//    }  
//      
//    /** 
//     * 获取 
//     * <br>------------------------------<br> 
//     */  
//    @Test  
//    public void testGetProvince() {  
//        String id = "province1";  
//        Province province = provinceDao.get(id);  
//        Assert.assertNotNull(province);  
//        Assert.assertEquals(province.getName(), "java2000_wl");  
//    }  
//  
//}
