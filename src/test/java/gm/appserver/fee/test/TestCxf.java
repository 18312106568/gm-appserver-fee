package gm.appserver.fee.test;

import com.google.gson.Gson;
import gm.appserver.fee.service.CenterWmsService;
import gm.common.utils.GsonUtils;
import gm.facade.fee.entity.wms.TransportAddress;
import org.springframework.beans.factory.aspectj.ConfigurableObject;
import gm.facade.fee.entity.wms.TransportBase;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

public class TestCxf {

    @Test
    public void testWmsService(){
        String address = "http://localhost:8700/services/centerwms?wsdl";
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress(address);
        factoryBean.setServiceClass(CenterWmsService.class);
        CenterWmsService service = (CenterWmsService) factoryBean.create();
        String result = service.addTransportBases("3"
                ,"[{\"ID\":{\"LOADING_LIST_ID\":1679825,\"RECEIPT_ID\":7873347},\"TRANSPORT_DATE\":\"Apr 24, 2019 12:00:00 AM\",\"DEPARTURE_TIME\":\"早730\",\"SHIPPING_TYPE\":\"汽运\",\"LOGISTICS_MODE\":\"城市配送\",\"CUSTOMER_NAME\":\"广州市从化区温泉镇卫生院\",\"CUSTOMER_CODE\":\"601986\",\"CUSTOMER_ID\":4839225,\"CUSTOMER_TYPE\":\"医疗\",\"DELIVERY_ADDR_ID\":9517172,\"DELIVERY_ADDRESS\":\"广东省广州市从化区温泉镇龙岗墟龙泉路12号4楼仓库\",\"DESTINATION_PROVINCES\":\"广东省\",\"DESTINATION_CITY\":\"广州市\",\"DESTINATION_CODE\":\"T010406\",\"DESTINATION_CHILD_CODE\":\"T010406-F001\",\"SRC_TRANSPORT_ADDRESS\":\"广州市增槎路1015号\",\"SRC_TRANSPORT_ADDRESS_ID\":275,\"ORIGIN_PROVINCES\":\"广东省\",\"ORIGIN_CITY\":\"广州市\",\"SRC_SHIPPING_CODE\":\"T010406\",\"SRC_CHILD_SHIPPING_CODE\":\"T010406-F001\",\"COST_PROVINCES\":\"广东省\",\"COST_CITY\":\"广州市\",\"COST_POINT_CODE\":\"T010406\",\"COST_CHILD_POINT_CODE\":\"T010406-F001\",\"TOTAL_BOX_NUM\":2,\"TOTAL_CHARGE_BOX_NUM\":2,\"RECEIPT_NUMBER\":2,\"REFRIGERATED_CONTAINERS_NUM\":0,\"TEMPERATURE_CONTROL_NUM\":0,\"RECEIPT_FLAG\":false,\"SHIPPER\":\"广州欣特医药有限公司\",\"REMARKS\":\"销售:2  \",\"CREATE_TIME\":\"Apr 23, 2019 9:01:08 PM\",\"LINE_NAME\":\"广州从化A区\",\"CARRIER\":\"北京盛世华人供应链管理有限公司\",\"RECEIPT_STATUS\":\"成功\",\"RECEIPT_TYPE\":\"客户签收单\",\"WAREHOUSE\":\"广州配送中心\",\"UNCONVENTIONAL_WORKING_FLAG\":\"0\",\"freightMode\":{\"name\":\"点运费+件运费+出车费\",\"createTime\":\"Jun 14, 2019 5:49:03 PM\",\"updateTime\":\"Jun 14, 2019 5:49:03 PM\",\"createBy\":\"0\",\"updateBy\":\"0\",\"id\":2182}}]");

        System.out.println(result);
    }

    @Test
    public void converToTransportBase(){
        Gson gson = new Gson();
        String dataStr = "{\"ID\":{\"LOADING_LIST_ID\":1679825,\"RECEIPT_ID\":7873347},\"TRANSPORT_DATE\":\"Apr 24, 2019 12:00:00 AM\",\"DEPARTURE_TIME\":\"早730\",\"SHIPPING_TYPE\":\"汽运\",\"LOGISTICS_MODE\":\"城市配送\",\"CUSTOMER_NAME\":\"广州市从化区温泉镇卫生院\",\"CUSTOMER_CODE\":\"601986\",\"CUSTOMER_ID\":4839225,\"CUSTOMER_TYPE\":\"医疗\",\"DELIVERY_ADDR_ID\":9517172,\"DELIVERY_ADDRESS\":\"广东省广州市从化区温泉镇龙岗墟龙泉路12号4楼仓库\",\"DESTINATION_PROVINCES\":\"广东省\",\"DESTINATION_CITY\":\"广州市\",\"DESTINATION_CODE\":\"T010406\",\"DESTINATION_CHILD_CODE\":\"T010406-F001\",\"SRC_TRANSPORT_ADDRESS\":\"广州市增槎路1015号\",\"SRC_TRANSPORT_ADDRESS_ID\":275,\"ORIGIN_PROVINCES\":\"广东省\",\"ORIGIN_CITY\":\"广州市\",\"SRC_SHIPPING_CODE\":\"T010406\",\"SRC_CHILD_SHIPPING_CODE\":\"T010406-F001\",\"COST_PROVINCES\":\"广东省\",\"COST_CITY\":\"广州市\",\"COST_POINT_CODE\":\"T010406\",\"COST_CHILD_POINT_CODE\":\"T010406-F001\",\"TOTAL_BOX_NUM\":2,\"TOTAL_CHARGE_BOX_NUM\":2,\"RECEIPT_NUMBER\":2,\"REFRIGERATED_CONTAINERS_NUM\":0,\"TEMPERATURE_CONTROL_NUM\":0,\"RECEIPT_FLAG\":false,\"SHIPPER\":\"广州欣特医药有限公司\",\"REMARKS\":\"销售:2  \",\"CREATE_TIME\":\"Apr 23, 2019 9:01:08 PM\",\"LINE_NAME\":\"广州从化A区\",\"CARRIER\":\"北京盛世华人供应链管理有限公司\",\"RECEIPT_STATUS\":\"成功\",\"RECEIPT_TYPE\":\"客户签收单\",\"WAREHOUSE\":\"广州配送中心\",\"UNCONVENTIONAL_WORKING_FLAG\":\"0\",\"freightMode\":{\"name\":\"点运费+件运费+出车费\",\"createTime\":\"Jun 14, 2019 5:49:03 PM\",\"updateTime\":\"Jun 14, 2019 5:49:03 PM\",\"createBy\":\"admin\",\"updateBy\":\"admin\",\"id\":2182}}";
        System.out.println(gson.fromJson(dataStr,TransportBase.class));
        System.out.println(dataStr);

    }

    @Test
    public void testAddress(){
        Gson gson = new Gson();
        String dataStr = "[{\"ADDRESSID\":123,\"ADDRESS\":\"123\",\"CUSTOMER_CODE\":\"123\",\"CONSIGNOR\":123,\"CONSIGNOR_NAME\":\"123\",\"CUSTOMTYPE\":\"123\"}]";
        String address = "http://localhost:8700/services/centerwms?wsdl";
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress(address);
        factoryBean.setServiceClass(CenterWmsService.class);
        CenterWmsService service = (CenterWmsService) factoryBean.create();
        String result = service.addTransportAddress("3"
                ,dataStr);
        System.out.println(result);
    }
}
