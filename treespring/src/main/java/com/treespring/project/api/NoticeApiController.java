package com.treespring.project.api;

import com.treespring.framework.web.controller.BaseController;
import com.treespring.framework.web.domain.AjaxResult;
import com.treespring.framework.web.page.TableDataInfo;
import com.treespring.project.system.domain.SysNotice;
import com.treespring.project.system.service.ISysDictTypeService;
import com.treespring.project.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告 信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/welcome/notice")
@CrossOrigin
@Configuration
public class NoticeApiController extends BaseController {
    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    private ISysDictTypeService dictTypeService;
    /**
     * 获取通知公告列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice) {
       // startPage();
        notice.setStatus("0");
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }
    @GetMapping("/getNoticeType")
    public AjaxResult getNoticeType() {
        return AjaxResult.success(dictTypeService.selectDictDataByType("sys_notice_type"));
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId) {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

}
