package com.tutu.trendsettercloud.bean;

import java.io.Serializable;
import java.util.List;

public class MillBean implements Serializable{
    private static final long serialVersionUID = 893872017218970664L;



    private String IP;
    private String ID;
    private String PublicKey;
    private List<String> Addresses;
    private String AgentVersion;
    private String ProtocolVersion;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public String getPublicKey() {
        return PublicKey;
    }

    public void setPublicKey(String publicKey) {
        PublicKey = publicKey;
    }

    public List<String> getAddresses() {
        return Addresses;
    }

    public void setAddresses(List<String> addresses) {
        Addresses = addresses;
    }

    public String getAgentVersion() {
        return AgentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        AgentVersion = agentVersion;
    }

    public String getProtocolVersion() {
        return ProtocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        ProtocolVersion = protocolVersion;
    }
}


/*
{
	"ID": "QmddRmYQeo3nmjaT39ieBv6DWzWThhawfonaNce7oLh3B5",
	"PublicKey": "CAASpgIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDYP4ynWDK7drG2cyvTcLPeFOFosrMubG1TtEdGj7gxe3jncdZGGpF4LUVhQNabr8GlZC2EyCj1B+Dqtql2902+f3f/U6cL3C4dKttD6UautPTHuC/YOzNaxPkNQthLypD5e+JV2Ul6Gagbo3E4GMXI0El/2fV8HvMfJtLqwalutZvwR+BWvyivYpDQJMjcDCZVav/v9FlkJMW5/zG7cj4Ya1ZiZ1018Q2TRs0EaXXLywu235//YMioVskBSVVDAnEUXeyp5PS6PgRwIYX/rpCRGE+xIA/vsvhmeXBFvysv21P0WG8VuqxawG5Mc8BxQuyVyYtDjxrhhC/ox6ZXgb0RAgMBAAE=",
	"Addresses": ["/ip4/127.0.0.1/tcp/4001/ipfs/QmddRmYQeo3nmjaT39ieBv6DWzWThhawfonaNce7oLh3B5", "/ip4/192.168.0.191/tcp/4001/ipfs/QmddRmYQeo3nmjaT39ieBv6DWzWThhawfonaNce7oLh3B5", "/ip6/::1/tcp/4001/ipfs/QmddRmYQeo3nmjaT39ieBv6DWzWThhawfonaNce7oLh3B5", "/ip4/113.88.163.250/tcp/9313/ipfs/QmddRmYQeo3nmjaT39ieBv6DWzWThhawfonaNce7oLh3B5"],
	"AgentVersion": "go-ipfs/0.4.14-rc1/",
	"ProtocolVersion": "ipfs/0.1.0"
}
 */