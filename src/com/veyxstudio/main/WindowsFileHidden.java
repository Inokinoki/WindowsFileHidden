package com.veyxstudio.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowsFileHidden
{
  static String[] text = {
    ".{20D04FE0-3AEA-1069-A2D8-08002B30309D}", 
    ".{645FF040-5081-101B-9F08-00AA002F954E}" };
  static JFrame window;
  static JButton hide;
  static JButton show;
  static JLabel help;
  static JPanel panel;
  static JPanel buttonPanel;
  static JTextField direction;
  static JButton choose;
  
  public static void main(String[] args) {InitialWindow();}
  
  private static void InitialWindow()
  {
    window = new JFrame();
    window.setTitle("Windows文件夹隐藏器 By Veyx Shaw");
    window.setSize(600, 150);
    window.setDefaultCloseOperation(3);
    window.setVisible(true);
    
    help = new JLabel();
    help.setText("选择一个文件夹，点击隐藏，看看有什么惊喜~");
    
    hide = new JButton();
    hide.setText("隐藏");
    hide.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {
        File target = new File(WindowsFileHidden.direction.getText());
        
        Random random = new Random(System.currentTimeMillis());
        File to;
        do
        {
          to = new File(target.getParent() + "\\" + target.getName() + WindowsFileHidden.text[random.nextInt(2)]);
        } while (to.exists());
        if (target.exists())
        {
          target.renameTo(new File(to.getPath()));
          WindowsFileHidden.help.setText("隐藏成功！您可选择另一个文件夹进行隐藏/解除隐藏~");
        }
        else
        {
          JOptionPane.showMessageDialog(null, "错误", "可能不存在的文件夹", 0);
        }
      }
    });
    show = new JButton();
    show.setText("显示");
    show.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {
        File target = new File(WindowsFileHidden.direction.getText());
        int flag = -1;
        for (int i = 0; i < WindowsFileHidden.text.length; i++) {
          if (target.getPath().contains(WindowsFileHidden.text[i]))
          {
            flag = i;
            break;
          }
        }
        if (flag != -1)
        {
          String toName = target.getPath().replace(WindowsFileHidden.text[flag], "");
          File to = new File(toName);
          target.renameTo(to);
          WindowsFileHidden.help.setText("解除隐藏成功！您可选择另一个文件夹进行隐藏/解除隐藏~");
        }
        else
        {
          JOptionPane.showMessageDialog(null, "错误", "解除伪装失败，请确认该文件夹源自本程序的伪装", 0);
        }
      }
    });
    direction = new JTextField();
    choose = new JButton();
    choose.setText("选择文件夹");
    choose.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(1);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == 0) {
          WindowsFileHidden.direction.setText(fileChooser.getSelectedFile().getPath());
        }
      }
    });
    buttonPanel = new JPanel();
    panel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(hide);
    buttonPanel.add(show);
    panel.setLayout(new BorderLayout(5, 5));
    panel.add(direction, "Center");
    panel.add(choose, "East");
    panel.add(help, "North");
    panel.add(buttonPanel, "South");
    


    window.setContentPane(panel);
    window.pack();
  }
}
