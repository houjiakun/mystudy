package com.study.excel;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExportMainController {

    @GetMapping("/export")
    public void testExport(HttpServletResponse response) throws Exception{
        String exportFileName = "测试.xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(exportFileName.getBytes(), "UTF-8"));

        List<TestPojo> list = new ArrayList();
        Map<String,Object> beans=new HashMap();
        Map t = new HashMap();
        t.put("name", "test");
        for (int i = 0; i <5 ; i++) {
            TestPojo pojo = new TestPojo();
            pojo.setId(i);
            pojo.setName("name " + i);
            list.add(pojo);
        }
        beans.put("list",list);
        XLSTransformer xlsTransformer = new XLSTransformer();
        InputStream is = this.getClass().getResourceAsStream("test.xlsx");
        try {
            Workbook hssfWorkBook = xlsTransformer.transformXLS(is, beans);
            hssfWorkBook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
