package com.tutu.trendsettercloud.api;

public class UrlConstants {


    /**
     *  Result说明：
     *  {"code":"SUCCESS","msg":"操作成功","data":null}
     *  code  : SUCCESS: 操作成功    SYSTEM_EXCEPTION:系统异常   NO_LOGIN:未登录    NO_AUTHEN:没有权限
     *  msg  : 结果说明
     *  data : 所需数据或者异常信息
     */

    private static final String HOST = "http://cktportal.szttsh.com/";

    private static final String HOST_IP = "http://192.168.0.125:8080/";

    /**
     *  用户注册
     *  phone	手机号
     *  password	登录密码
     *  ethId	ethId
     *  verificationCode	验证码
     *  {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String REGISTER_URL = HOST + "sysuser/ajaxRegs";

    /**
     * 用户登录
     *   phone	手机号
     *   password	登录密码
     *  {"code":"SUCCESS","msg":"操作成功","data":{}}
     */
    public static final String LOGIN_URL = HOST + "sysuser/ajaxLogin";

    /**
     * 用户修改
     *    id	用户id	允许为空(Y/N)
     *   nickName	昵称	Y
     *  {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String MODIFY_URL = HOST + "sysuser/modify";

    /**
     * 密码修改
     *      id	Id
     *     phone	手机号
     *     password	新密码
     *     oldPwd	旧密码
     *     verificationCode	验证码
     *    {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String MODIFY_PWD_URL = HOST + "sysuser/modifyPwd";


    /**
     * 密码重置
     *      id	Id
     *     phone	手机号
     *     password	新密码
     *     verificationCode	验证码
     *    {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String RESET_PWD_URL = HOST + "sysuser/resetPwd";


    /**
     * 添加联系人
     *  sourceUserId	用户ID
     *  targetUserId	被添加用户ID
     *    {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String CREATE_CONTACTS_URL = HOST + "contact/create";


    /**
     * 获取联系人
     *  sourceUserId	用户ID
     *      {"code":"SUCCESS","msg":"操作成功","data":{}}
     */
    public static final String GET_CONTACTS_URL = HOST + "contact/getGrid";

    /**
     * 添加挖矿记录
     *    currencyValue	挖矿所得币值
     *    miningDatetime	挖矿时间
     *         {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String CREATE_MININGREC_URL = HOST + "miningRec/create";


    /**
     * 获取挖矿记录
     *  ethId	用户ethId
     *  pageNum	当前页码
     *  pageSize	分页大小
     *       {"code":"SUCCESS","msg":"操作成功",
     "data":[{
     createDatetime:"2018-06-19 17:36:19",   //创建时间
     currencyValue:2,  //币数量
     ethId:"12345",   //ethId
     id:"2",   //id
     lastUpdateDatetime:"2018-06-19 17:36:19",  //最后更新时间
     mineMachineId:"12345",    //矿机Id
     miningDatetime:"2018-06-19 17:29:00"   //币获取时间
     }]
     */
    public static final String GET_MININGREC_URL = HOST + "miningRec/getGrid";


    /**
     * 添加需要备份的矿机信息
     *    machineId	矿机id
     *    fileHash	文件哈希值
     *     type	0：备份文件hash 1：根目录hash
     *           {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String CREATE_BACKUP_URL = HOST + "backups/create";

    /**
     * 获取需要备份的文件hash信息
     *    machineId	矿机id
     *            {"code":"SUCCESS","msg":"操作成功","data":["AAAAAAAAAAAAAAAA","sssssssssss"]}
     */
    public static final String GET_FILE_HASH_URL = HOST + "backups/getFileHash";


    /**
     * 获取矿机根目录hash信息
     *    machineId	矿机id
     *   {"code":"SUCCESS","msg":"操作成功","data":"3333333"
     */
    public static final String GET_FILE_HASH_URL1 = HOST + "backups/getFileHash";

    /**
     * 获取短信验证码
     *    {"code":"SUCCESS","msg":"操作成功","data":null}
     */
    public static final String GET_CODE_URL = HOST + "msg/sendMsg";









    }
