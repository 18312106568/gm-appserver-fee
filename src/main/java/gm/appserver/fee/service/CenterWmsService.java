package gm.appserver.fee.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "CenterWmsService", // 暴露服务名称
        targetNamespace = "http://service.fee.appserver.gm/"
)
public interface CenterWmsService {

    @WebResult(name = "E_PUT")
    @WebMethod(action = "http://service.fee.appserver.gm/addTransportBases")
    String addTransportBases(@WebParam(name = "I_SYSTEM")String system
            ,@WebParam(name = "I_PUT") String datas);

    @WebResult(name = "E_PUT")
    @WebMethod(action = "http://service.fee.appserver.gm/addTransportAddress")
    String addTransportAddress(@WebParam(name = "I_SYSTEM")String system
            ,@WebParam(name = "I_PUT") String datas);
}
