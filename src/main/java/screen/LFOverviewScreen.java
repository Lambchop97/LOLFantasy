package screen;

import components.LFInnerOverview;
import components.LFTabPanel;

import javax.swing.*;
import java.awt.*;

public class LFOverviewScreen extends LFScreen {

    public LFOverviewScreen(){
        super();
        content.setMaximumSize(new Dimension(1280,720));

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        LFTabPanel tabs = new LFTabPanel("overview");

        LFInnerOverview innerContent = new LFInnerOverview();

        content.add(tabs.getContent());
        content.add(innerContent.getContent());
    }

}
