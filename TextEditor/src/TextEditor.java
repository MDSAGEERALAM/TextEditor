import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar manuBar;
    JMenu file , edit;

    // file manu item
    JMenuItem newFile , openFile , saveFile;
    // edit manu item
    JMenuItem cut , copy , paste , selectAll , close;

    JTextArea textArea;  // text area created
    TextEditor() {
        // initilize frame
        frame = new JFrame();
        // initilize jmanuBar
        manuBar = new JMenuBar();

        // initilize text Area
        textArea = new JTextArea();
        // initilize manus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // initilize file manuItems
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("save File");
        // add actionlistner to the file manu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // add manu items to file manu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initilize Edit manuItem
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // adding action listners to the edit manu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // add to edit manu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add manu to manuBar
        manuBar.add(file);
        manuBar.add(edit);
        //set diminsions of frame
       // frame.add(textArea);

        frame.setJMenuBar(manuBar);
        // create contant pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5 , 5 , 5 , 5));
        panel.setLayout(new BorderLayout( 0 , 0));
        // add text area to the panel
        panel.add(textArea , BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
        frame.setBounds(100, 100, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
        @Override
                public void actionPerformed(ActionEvent actionEvent)
        {
            if(actionEvent.getSource()==cut)
            {
                // perform cut operation
                textArea.cut();
            }
            if(actionEvent.getSource()==copy)
            {
                // perform copy operation
                textArea.copy();
            }
            if(actionEvent.getSource()==paste)
            {
                // perform paste operation
                textArea.paste();
            }
            if(actionEvent.getSource()==selectAll)
            {
                // perform selectall operation
                textArea.selectAll();
            }
            if(actionEvent.getSource()==close)
            {
                // perform cut operation
                System.exit(0); // exit from code
            }
            if(actionEvent.getSource()==openFile)
            {
                // open a file chooser
                JFileChooser fileChooser=new JFileChooser("C:");
                int chooseOption=fileChooser.showOpenDialog(null);
                // if we have click on open button
                if(chooseOption==JFileChooser.APPROVE_OPTION)
                {
                    // getting sellected file
                    File file=fileChooser.getSelectedFile();
                    //get the path of selected file
                    String filePath=file.getPath();
                    try{
                        // initilize file reader
                        FileReader fileReader=new FileReader(filePath);
                        // initilize buffered reader
                        BufferedReader bufferedReader=new BufferedReader(fileReader);
                        String intermediate="" , output="";
                        // read containts of file line by line
                        while((intermediate=bufferedReader.readLine()) !=null)
                        {
                            output+=intermediate+"\n";
                        }
                        // set the output string
                        textArea.setText(output);
                    }
                    catch(IOException fileNotFoundException){
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
            if(actionEvent.getSource()==saveFile){
                // initilize file picker
                JFileChooser fileChooser=new JFileChooser("C:");
                // get choose option from file chooser
                int chooseOption=fileChooser.showOpenDialog(null);
                // checked if we cliked on save buttion
                if(chooseOption==JFileChooser.APPROVE_OPTION){
                    // create a new file with chosen directry path and file name
                    File file=new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                    try{
                        // initilize file writer
                        FileWriter fileWriter=new FileWriter(file);
                        // initilize Buffered writer
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        // write contents of the text area to file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch(IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            }
            if(actionEvent.getSource()==newFile)
            {
                TextEditor newTextEditor=new TextEditor();
            }

        }

    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}