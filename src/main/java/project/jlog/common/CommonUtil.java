package project.jlog.common;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component //스프링부트가 관리하는 빈으로 등록
public class CommonUtil {
    public String markdown(String markdown){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
    //마크다운 텍스트를 HTML 문서로 변환하여 리턴
}
