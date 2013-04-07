package screens;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.LabelField;

public class CustomText extends LabelField
{
    private int color;
    
    public CustomText(Object text, Font font, int color,long style)
    {
        super(text, style);
        
        this.color = color;
        setFont(font);
    }
    
    public void paint(Graphics g)
    {
        g.setColor(color);
        super.paint(g);
    }    
} 
