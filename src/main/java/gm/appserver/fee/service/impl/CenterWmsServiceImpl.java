package gm.appserver.fee.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gm.appserver.fee.service.CenterWmsService;
import gm.appserver.fee.vo.TransportResponseVo;
import gm.common.base.annotation.ParamterLog;
import gm.common.utils.GsonUtils;
import gm.facade.fee.entity.wms.TransportAddress;
import gm.facade.fee.entity.wms.TransportBase;
import gm.facade.fee.service.TransportBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import javax.jws.WebService;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;


@Component
@WebService(name = "CenterWmsService", // 暴露服务名称
        targetNamespace = "http://service.fee.appserver.gm/",// 命名空间,一般是接口的包名倒序
        endpointInterface = "gm.appserver.fee.service.CenterWmsService"
)
@Slf4j
public class CenterWmsServiceImpl implements CenterWmsService {

    @Reference(version = "1.0.0")
    TransportBaseService transportBaseService;

    @ParamterLog
    @Override
    public String addTransportBases(String system,String datas) {
        Gson gson = GsonUtils.getGson();
        List<TransportBase> transportBaseList = null;
        List<TransportResponseVo> responseVos = new ArrayList<>();
        //1.基础数据转json
        try {
             transportBaseList =
                    gson.fromJson(datas,new TypeToken<List<TransportBase>>(){}.getType());
        }catch(Exception ex){
            log.error("转换json失败");
            throw new RuntimeException(ex);
        }
        if(transportBaseList==null|| transportBaseList.isEmpty()){
            throw new RuntimeException("计费基础信息为空");
        }

        //2.调用服务，逐条新增计费基础数据
        for(TransportBase transportBase : transportBaseList){
            //信息修改标志默认为flag
            transportBase.setInformationModificationMark(false);
            transportBase.setLockFlag(false);
            TransportResponseVo responseVo = new TransportResponseVo();
            responseVo.setLoadingListId(transportBase.getId().getLoadingListId());
            responseVo.setReceiptId(transportBase.getId().getReceiptId());

            try {
                //查询是否已有签收单数据，对未存在的直接更新
                TransportBase oldTransportBase = transportBaseService.getTransportBase(transportBase.getId());
                if(oldTransportBase==null) {
                    transportBaseService.addTransportBase(transportBase);
                }else{
                    transportBaseService.addTransportBase(oldTransportBase);
                }
                responseVo.setMsgty(TransportResponseVo.SUCCESS);
                responseVo.setMessage(TransportResponseVo.SUCCESS_MSG);
                continue;
            }catch(Exception ex){
                responseVo.setMsgty(TransportResponseVo.FAIL);
                responseVo.setMessage(ex.toString());
                ex.getCause().printStackTrace();
            }

            responseVos.add(responseVo);
        }

        //3.将响应结果转为json
        return gson.toJson(responseVos);
    }

    @ParamterLog
    @Override
    public String addTransportAddress(String system, String datas) {
        Gson gson = GsonUtils.getGson();
        List<TransportAddress> transportAddresses = null;
        List<TransportResponseVo> transportResponseVos = new ArrayList<>();

        //1.转json为对象
        transportAddresses =  gson.fromJson(datas,new TypeToken<List<TransportAddress>>(){}.getType());

        //2.逐个调用接口服务
        for(TransportAddress address : transportAddresses){
            TransportResponseVo responseVo = new TransportResponseVo();
            responseVo.setAddressId(address.getAddressid());

            try {
                transportBaseService.addAddress(address);
                responseVo.setMsgty(TransportResponseVo.SUCCESS);
                responseVo.setMessage(TransportResponseVo.SUCCESS_MSG);
            }catch(Exception ex){
                responseVo.setMsgty(TransportResponseVo.FAIL);
                responseVo.setMessage(ex.toString());
                ex.printStackTrace();
            }
            transportResponseVos.add(responseVo);
        }

        //3.将响应结果转为json
        return gson.toJson(transportResponseVos);
    }
}
