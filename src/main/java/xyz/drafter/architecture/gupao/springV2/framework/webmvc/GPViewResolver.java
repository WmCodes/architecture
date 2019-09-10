package xyz.drafter.architecture.gupao.springV2.framework.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangmeng
 * @date 2019/9/6
 * @desciption 将静态文件变为一个动态文件
 *   根据用户传送的参数不同产生不同的结果
 */
public class GPViewResolver {

    private String viewName;
    private File templatFile;

    public GPViewResolver(String viewName, File templatFile){
        this.viewName = viewName;
        this.templatFile = templatFile;
    }

    public String viewResolver(GPModelAndView mv) throws Exception{

        // mv转换成字符串
        StringBuffer sb = new StringBuffer();
        RandomAccessFile ra = new RandomAccessFile(this.templatFile, "r");
        String line = null;
        while (null != (line = ra.readLine())){
            Matcher m = matcher(line);
            while (m.find()){
                for (int i = 1;i<=m.groupCount();i++){
                    // #{11} 取出中间的字符串
                    String paramName = m.group(i);
                    Object paramValue = mv.getModel().get(paramName);
                    if (null == paramName){continue;}
                    line = line.replace("#\\{"+paramName+"\\}", paramValue.toString());


                }
            }
            sb.append(line);
        }

        return null;
    }


    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public File getTemplatFile() {
        return templatFile;
    }

    public void setTemplatFile(File templatFile) {
        this.templatFile = templatFile;
    }

    private Matcher matcher(String str){
        Pattern pattern = Pattern.compile("Y\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }
}
