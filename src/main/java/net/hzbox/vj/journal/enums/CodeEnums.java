package net.hzbox.vj.journal.enums;

import lombok.Getter;

/**
 * 返回信息统一存放
 * 返回码可以归为两类，一类为统一的，可以用小数字直接表示；
 * 1 : 为 成功 返回！ 不！许！改！
 * 第二类为模块独有的 已 模块 + 方法 + 返回类型 的格式返回，
 * 例如：定义会议controller中返回的内容统一以1开头，新增接口定为
 * 当前模块的第一个方法 10101 为***错误  10102 为***错误 统一管理。
 * Created by zb on 2017/10/11.
 */
@Getter
public enum CodeEnums {

    SUCCESS(1, "请求成功唯一返回码，不许改！"),
    PARAMETER_EMPTY(2, "参数不能为空！"),
    RECORD_NON_EXISTENT(3, "记录不存在！"),
    THROW_EXCEPTION(4, "报异常了！"),

    MEET_DOWNLOAD_NON_FILES(10101, "当前附件不存在！"),
    MEET_DOWNLOAD_GET_ACCESS_TOKEN_ERROR(10102, "获取accessToken失败！"),
    MEET_DOWNLOAD_GET_PERSON_ERROR(10102, "获取人员信息失败！"),
    MEET_ROOM_PLEASE_CONFIRM(20101, "还有未进行的会议，确认是否停用？"),
    MEET_ROOM_DELETE_CONFIRM(20102, "还有未进行的会议，确认是否删除？"),;

    private Integer status;
    private String description;


    CodeEnums(Integer status, String description) {
        this.status = status;
        this.description = description;
    }
}
