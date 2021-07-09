/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.information;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public final class mainf extends javax.swing.JFrame {

    String get_selected_semester=null;
     String get_selected_dept=null;
     String add_get_selected_dept=null;
     String add_get_selected_dept1=null;
    String selectedimagepath=null;
    Image image;
    public mainf() {
        
        initComponents();
        ImageIcon icon=new ImageIcon(getClass().getResource("logo1.png"));
        Image scaleimage=icon.getImage();
        Image scaledimg=scaleimage.getScaledInstance(jnuicon.getWidth(), jnuicon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon img=new ImageIcon(scaledimg);
        jnuicon.setIcon(img);
        
        
         show_info.setEnabled(false);
         add_information.setEnabled(false);
         search_std.setEnabled(false);
         edit_info.setEnabled(false);
         dept_info.setEnabled(false);
        
        
        switchPanel(dashboard);
      
        //department panel initialization
        {
            //auto update department list
                    String url="jdbc:mysql://127.0.0.1/sis";
                    String username="root";
                    String password="";
                    PreparedStatement pst=null;

                    String query="SELECT * FROM `departments` WHERE 1";
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection con=DriverManager.getConnection(url,username,password);
                        java.sql.ResultSet result;
                        Statement stmt=(Statement) con.createStatement();
                        result=stmt.executeQuery(query);
                        DefaultListModel model=new DefaultListModel();
                        while(result.next())
                        {
                            String dept=result.getString("Department_name");
                            model.addElement(dept);
                             dept_list.setModel(model);
                        }

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
            //selected department
                    
                    dept_list.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            get_selected_dept= dept_list.getSelectedValue();
                            
                        }
                    });
                    
                    semester_list.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            DefaultTableModel model = (DefaultTableModel) dept_course_table.getModel();
                            ((DefaultTableModel)dept_course_table.getModel()).setRowCount(0);
                            sem_alert.setText("");
                           get_selected_semester= (String) semester_list.getSelectedItem();
                           if("Choose Semester".equals(get_selected_semester))
                           {
                               sem_alert.setText("?");
                               
                           }
                           else
                           {
                               

                                //System.out.println(get_selected_semester);
                                String query="SELECT * FROM `courses` WHERE `department`='"+get_selected_dept+"' AND `Semester`='"+get_selected_semester+"'";
                                try {
                                    Class.forName("com.mysql.jdbc.Driver");
                                    java.sql.Connection con=DriverManager.getConnection(url,username,password);
                                    java.sql.ResultSet result;
                                    Statement stmt=(Statement) con.createStatement();
                                    result=stmt.executeQuery(query);

                                    while(result.next())
                                    {
                                        String course=result.getString("course name");
                                        String course_id=result.getString("course id");
                                        String course_mark=result.getString("marks");
                                        String course_credit=result.getString("credit");	
                                        model.addRow(new Object[]{course, course_id,course_mark,course_credit});
                                    }

                                } catch (ClassNotFoundException | SQLException ex) {
                                    Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
                                }
                           }

                           }
                                   
                        
                    });
                    
                   // add info
                   {
        

                        query="SELECT * FROM `departments` WHERE 1";
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            java.sql.Connection con=DriverManager.getConnection(url,username,password);
                            java.sql.ResultSet result;
                            Statement stmt=(Statement) con.createStatement();
                            result=stmt.executeQuery(query);
                            
                            DefaultListModel model=new DefaultListModel();
                            while(result.next())
                            {
                               String dept=result.getString("Department_name");
                               std_dpt.addItem(dept);
                            }

                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                    }
                   
                   std_dpt.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                           add_get_selected_dept= (String) std_dpt.getSelectedItem();
                           }
                    });
                   
                   // add info ends
                   // update info starts
                   std_dpt1.addItemListener(new ItemListener() {
                             @Override
                             public void itemStateChanged(ItemEvent e) {
                                add_get_selected_dept1= (String) std_dpt1.getSelectedItem();
                                }
                    });
                   
                   //update info ends
                    
                   
                   //about image codings
                   ImageIcon icon2=new ImageIcon(getClass().getResource("mam.jpg"));
                   Image scaleimage2=icon2.getImage();
                   Image scaledimg2=scaleimage2.getScaledInstance(mam.getWidth(), mam.getHeight(), Image.SCALE_SMOOTH);
                   ImageIcon img2=new ImageIcon(scaledimg2);
                   mam.setIcon(img2);
                   
                   
                   //about ends
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        std_gender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jnuicon = new javax.swing.JLabel();
        log_in = new javax.swing.JButton();
        log_out = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Dashboard = new javax.swing.JButton();
        add_information = new javax.swing.JButton();
        show_info = new javax.swing.JButton();
        search_std = new javax.swing.JButton();
        dept_info = new javax.swing.JButton();
        aboutsw = new javax.swing.JButton();
        layer = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        dashboard = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        mam = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lOG_IN = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pf = new javax.swing.JPasswordField();
        uf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        sign_in = new javax.swing.JButton();
        add_info = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        std_name = new javax.swing.JTextField();
        std_mname = new javax.swing.JTextField();
        std_paddress = new javax.swing.JTextField();
        std_fname = new javax.swing.JTextField();
        std_phone = new javax.swing.JTextField();
        std_praddress = new javax.swing.JTextField();
        std_blood = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        std_email = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        std_gender_male = new javax.swing.JRadioButton();
        std_gender_female = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        std_sessionf = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        std_id = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        std_dob_day = new javax.swing.JTextField();
        std_dob_year = new javax.swing.JTextField();
        std_dob_month = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        std_sessionl = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        std_imagedisplay = new javax.swing.JLabel();
        std_dpt = new javax.swing.JComboBox<>();
        add_update_checker = new javax.swing.JLabel();
        add_dept_name_check = new javax.swing.JLabel();
        edit_info = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        std_name1 = new javax.swing.JTextField();
        std_mname1 = new javax.swing.JTextField();
        std_paddress1 = new javax.swing.JTextField();
        std_fname1 = new javax.swing.JTextField();
        std_phone1 = new javax.swing.JTextField();
        std_praddress1 = new javax.swing.JTextField();
        std_blood1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        std_email1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        std_gender_male1 = new javax.swing.JRadioButton();
        std_gender_female1 = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        std_sessionf1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        std_id1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        std_dob_day1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        std_search_1_value = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        std_search_1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        std_imagedisplay1 = new javax.swing.JLabel();
        std_dpt1 = new javax.swing.JComboBox<>();
        add_update_checker1 = new javax.swing.JLabel();
        Department = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        get_dept_name = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dept_course_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        dept_list = new javax.swing.JList<>();
        jLabel40 = new javax.swing.JLabel();
        dept_add_checker = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        dept_add_course = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        get_dept_course = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        dept_course_add_checker = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        get_dept_course_id = new javax.swing.JTextField();
        semester_list = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        get_marks = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        get_credit = new javax.swing.JTextField();
        sem_alert = new javax.swing.JLabel();
        search = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        search_get_data = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        search_result = new javax.swing.JTable();
        about = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(240, 247, 243));

        jnuicon.setBackground(new java.awt.Color(204, 204, 255));

        log_in.setBackground(new java.awt.Color(228, 241, 254));
        log_in.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        log_in.setText("Log In");
        log_in.setBorder(null);
        log_in.setBorderPainted(false);
        log_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_inActionPerformed(evt);
            }
        });

        log_out.setBackground(new java.awt.Color(228, 241, 254));
        log_out.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        log_out.setText("Log Out");
        log_out.setBorder(null);
        log_out.setBorderPainted(false);
        log_out.setEnabled(false);
        log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_outActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel21.setText("Student Information System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jnuicon, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(log_out, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(log_in, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(log_in, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(log_out, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jnuicon, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {log_in, log_out});

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 140));

        jPanel3.setBackground(new java.awt.Color(34, 49, 63));

        Dashboard.setBackground(new java.awt.Color(228, 241, 254));
        Dashboard.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Dashboard.setText("Dashboard");
        Dashboard.setBorder(null);
        Dashboard.setBorderPainted(false);
        Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashboardActionPerformed(evt);
            }
        });

        add_information.setBackground(new java.awt.Color(228, 241, 254));
        add_information.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        add_information.setText("Add Information");
        add_information.setBorder(null);
        add_information.setBorderPainted(false);
        add_information.setEnabled(false);
        add_information.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_informationActionPerformed(evt);
            }
        });

        show_info.setBackground(new java.awt.Color(228, 241, 254));
        show_info.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        show_info.setText("Edit/Show Info");
        show_info.setBorder(null);
        show_info.setBorderPainted(false);
        show_info.setEnabled(false);
        show_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_infoActionPerformed(evt);
            }
        });

        search_std.setBackground(new java.awt.Color(228, 241, 254));
        search_std.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        search_std.setText("Search Student");
        search_std.setBorder(null);
        search_std.setBorderPainted(false);
        search_std.setEnabled(false);
        search_std.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_stdActionPerformed(evt);
            }
        });

        dept_info.setBackground(new java.awt.Color(228, 241, 254));
        dept_info.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dept_info.setText("Department");
        dept_info.setBorder(null);
        dept_info.setBorderPainted(false);
        dept_info.setEnabled(false);
        dept_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dept_infoActionPerformed(evt);
            }
        });

        aboutsw.setBackground(new java.awt.Color(228, 241, 254));
        aboutsw.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        aboutsw.setText("About");
        aboutsw.setBorder(null);
        aboutsw.setBorderPainted(false);
        aboutsw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutswActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_information, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(show_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_std, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dept_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aboutsw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(add_information, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(show_info, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search_std, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dept_info, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aboutsw, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, 560));

        layer.setBackground(new java.awt.Color(190, 222, 222));

        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        dashboard.setBackground(new java.awt.Color(190, 222, 222));

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Welcome to.. ");

        mam.setToolTipText("");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 42)); // NOI18N
        jLabel48.setText("Student Information System!");

        javax.swing.GroupLayout dashboardLayout = new javax.swing.GroupLayout(dashboard);
        dashboard.setLayout(dashboardLayout);
        dashboardLayout.setHorizontalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(mam, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel48)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        dashboardLayout.setVerticalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addGap(18, 18, 18)
                .addComponent(mam, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jLayeredPane1.add(dashboard);

        lOG_IN.setBackground(new java.awt.Color(190, 222, 222));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Hi there, Please Sign in to Continue");

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        jLabel1.setText("Username");

        pf.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        uf.setToolTipText("Enter your your username!");

        jLabel5.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        jLabel5.setText("Password");

        sign_in.setBackground(new java.awt.Color(255, 255, 255));
        sign_in.setFont(new java.awt.Font("Perpetua", 0, 36)); // NOI18N
        sign_in.setText("Sign In");
        sign_in.setBorder(null);
        sign_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sign_inActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lOG_INLayout = new javax.swing.GroupLayout(lOG_IN);
        lOG_IN.setLayout(lOG_INLayout);
        lOG_INLayout.setHorizontalGroup(
            lOG_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lOG_INLayout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(lOG_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lOG_INLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lOG_INLayout.createSequentialGroup()
                        .addGroup(lOG_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(46, 46, 46)
                        .addGroup(lOG_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pf, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(uf))
                        .addGap(155, 155, 155))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lOG_INLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sign_in, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
        );
        lOG_INLayout.setVerticalGroup(
            lOG_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lOG_INLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(lOG_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(uf, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(lOG_INLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lOG_INLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lOG_INLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(pf, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addComponent(sign_in, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jLayeredPane1.add(lOG_IN);

        add_info.setBackground(new java.awt.Color(190, 222, 222));
        add_info.setPreferredSize(new java.awt.Dimension(694, 499));

        jLabel7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel7.setText("Enter Student's Information !");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Full Name         :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Mothers Name   :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Fathers Name    :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Present Address :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Phone Number  :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Perm. Address   :");

        std_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_nameActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Blood Group     :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Email Address   :");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Add Information");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Gender            :");

        std_gender.add(std_gender_male);
        std_gender_male.setText("male");
        std_gender_male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_gender_maleActionPerformed(evt);
            }
        });

        std_gender.add(std_gender_female);
        std_gender_female.setText("female");
        std_gender_female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_gender_femaleActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Session     :");

        std_sessionf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_sessionfActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Department      :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Student ID       :");

        std_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_idActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Date of Birth :");

        std_dob_day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_dob_dayActionPerformed(evt);
            }
        });

        std_dob_year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_dob_yearActionPerformed(evt);
            }
        });

        std_dob_month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_dob_monthActionPerformed(evt);
            }
        });

        jLabel19.setText("Day/Month/Year");

        std_sessionl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_sessionlActionPerformed(evt);
            }
        });

        jLabel22.setText("/");

        jButton4.setText("Choose Image");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        std_dpt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Department" }));

        add_dept_name_check.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N

        javax.swing.GroupLayout add_infoLayout = new javax.swing.GroupLayout(add_info);
        add_info.setLayout(add_infoLayout);
        add_infoLayout.setHorizontalGroup(
            add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_infoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_infoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(std_gender_male, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(std_gender_female, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(add_infoLayout.createSequentialGroup()
                        .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(add_infoLayout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(std_praddress))
                                .addGroup(add_infoLayout.createSequentialGroup()
                                    .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(std_paddress)
                                        .addComponent(std_mname)
                                        .addComponent(std_fname)
                                        .addComponent(std_name)))
                                .addGroup(add_infoLayout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(std_phone, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                                .addComponent(jLabel13)
                                .addGroup(add_infoLayout.createSequentialGroup()
                                    .addGap(142, 142, 142)
                                    .addComponent(std_blood))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_infoLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(45, 45, 45)
                                    .addComponent(std_sessionf, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(7, 7, 7)
                                    .addComponent(jLabel22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(std_sessionl, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)))
                            .addGroup(add_infoLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(std_email, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)
                            .addGroup(add_infoLayout.createSequentialGroup()
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(10, 10, 10)
                                .addComponent(std_dob_day, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(std_dob_month, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(std_dob_year, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(add_infoLayout.createSequentialGroup()
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(std_dpt, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(std_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(add_dept_name_check, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_infoLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(103, 103, 103))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_infoLayout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_infoLayout.createSequentialGroup()
                        .addComponent(std_imagedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_infoLayout.createSequentialGroup()
                        .addComponent(add_update_checker, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_infoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
        );

        add_infoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel2, jLabel8, jLabel9, std_blood, std_email, std_fname, std_mname, std_name, std_paddress, std_phone, std_praddress});

        add_infoLayout.setVerticalGroup(
            add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_infoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(add_infoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(add_infoLayout.createSequentialGroup()
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(add_infoLayout.createSequentialGroup()
                                        .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(std_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9))
                                    .addGroup(add_infoLayout.createSequentialGroup()
                                        .addComponent(std_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(std_mname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(std_paddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_infoLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(2, 2, 2))
                                    .addComponent(std_praddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(std_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(std_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(std_blood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(std_dpt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addComponent(add_dept_name_check)
                                .addGap(3, 3, 3)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(std_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(add_infoLayout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(38, 38, 38)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(add_infoLayout.createSequentialGroup()
                                        .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(std_gender_male)
                                            .addComponent(std_gender_female))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel18)
                                            .addComponent(std_dob_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(std_dob_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(std_dob_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addGroup(add_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(std_sessionf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(std_sessionl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel22))))))
                            .addGroup(add_infoLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(std_imagedisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addComponent(add_update_checker, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        add_infoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel2, jLabel8, jLabel9, std_blood, std_email, std_fname, std_mname, std_name, std_paddress, std_phone, std_praddress});

        jLayeredPane1.add(add_info);

        edit_info.setBackground(new java.awt.Color(190, 222, 222));
        edit_info.setPreferredSize(new java.awt.Dimension(694, 499));

        jLabel23.setFont(new java.awt.Font("Century", 0, 36)); // NOI18N
        jLabel23.setText("Check Student's Information !");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel24.setText("Full Name         :");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel25.setText("Mothers Name   :");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel26.setText("Fathers Name    :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel27.setText("Present Address :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel28.setText("Phone Number  :");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel29.setText("Perm. Address   :");

        std_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_name1ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel30.setText("Blood Group     :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel31.setText("Email Address   :");

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setText("Update");
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel32.setText("  Gender          :");

        std_gender.add(std_gender_male1);
        std_gender_male1.setText("male");
        std_gender_male1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_gender_male1ActionPerformed(evt);
            }
        });

        std_gender.add(std_gender_female1);
        std_gender_female1.setText("female");
        std_gender_female1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_gender_female1ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel33.setText("Session          :");

        std_sessionf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_sessionf1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel34.setText("Department      :");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel35.setText("Student ID       :");

        std_id1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_id1ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel36.setText("Date of Birth   :");

        std_dob_day1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_dob_day1ActionPerformed(evt);
            }
        });

        jButton6.setText("Choose Image");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        std_search_1_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_search_1_valueActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel37.setText("Search Student Information by Student id");

        std_search_1.setText("Search");
        std_search_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                std_search_1ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout edit_infoLayout = new javax.swing.GroupLayout(edit_info);
        edit_info.setLayout(edit_infoLayout);
        edit_infoLayout.setHorizontalGroup(
            edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_infoLayout.createSequentialGroup()
                .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_infoLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(edit_infoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(edit_infoLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(std_blood1))
                            .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(7, 7, 7)
                                        .addComponent(std_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(6, 6, 6)
                                        .addComponent(std_fname1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(6, 6, 6)
                                        .addComponent(std_mname1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(std_paddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(std_praddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(std_phone1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel31)
                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                        .addGap(129, 129, 129)
                                        .addComponent(std_email1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(edit_infoLayout.createSequentialGroup()
                                    .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_infoLayout.createSequentialGroup()
                                            .addComponent(jLabel34)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(std_dpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(edit_infoLayout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(edit_infoLayout.createSequentialGroup()
                                                    .addComponent(jLabel35)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(std_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel36)
                                                            .addComponent(jLabel33))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(std_sessionf1)
                                                            .addComponent(std_dob_day1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(edit_infoLayout.createSequentialGroup()
                                                        .addComponent(jLabel32)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(std_gender_male1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(std_gender_female1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGap(1, 1, 1))))
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(edit_infoLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(std_imagedisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_infoLayout.createSequentialGroup()
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))))
                            .addGroup(edit_infoLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_infoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(std_search_1_value, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(std_search_1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3)
                                        .addGap(10, 10, 10))
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(edit_infoLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(add_update_checker1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        edit_infoLayout.setVerticalGroup(
            edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edit_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(3, 3, 3)
                .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edit_infoLayout.createSequentialGroup()
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(std_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addGroup(edit_infoLayout.createSequentialGroup()
                                .addComponent(std_fname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(std_mname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(std_paddress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edit_infoLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(2, 2, 2))
                            .addComponent(std_praddress1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(std_phone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(std_email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(std_blood1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(std_dpt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(std_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(std_gender_male1)
                            .addComponent(std_gender_female1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(std_dob_day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(std_sessionf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(edit_infoLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(std_search_1_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(std_search_1)
                            .addComponent(jButton3))
                        .addGap(18, 18, 18)
                        .addComponent(std_imagedisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(edit_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_update_checker1))))
                .addContainerGap())
        );

        edit_infoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {std_blood1, std_email1, std_fname1, std_id1, std_mname1, std_name1, std_paddress1, std_phone1, std_praddress1});

        jLayeredPane1.add(edit_info);

        Department.setBackground(new java.awt.Color(190, 222, 222));

        jLabel38.setFont(new java.awt.Font("Lucida Fax", 0, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Department");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("Add Department");

        get_dept_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_dept_nameActionPerformed(evt);
            }
        });

        jLabel39.setText("Name :");

        jButton7.setText("ADD");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        dept_course_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Name", "Course Id", "Total Mark", "Total Credit"
            }
        ));
        jScrollPane1.setViewportView(dept_course_table);

        dept_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dept_listMouseClicked(evt);
            }
        });
        dept_list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                dept_listValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(dept_list);

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("Department List");

        dept_add_checker.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N

        jButton1.setText("update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Course Name's and Course ID's");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Semester Selection");

        dept_add_course.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dept_add_course.setText("Add Course");

        jLabel45.setText("Course Name :");

        get_dept_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_dept_courseActionPerformed(evt);
            }
        });

        jButton8.setText("ADD");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        dept_course_add_checker.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N

        jLabel46.setText("Course Id :");

        get_dept_course_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_dept_course_idActionPerformed(evt);
            }
        });

        semester_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Semester", "1st Semester", "2nd Semester", "3rd Semester", "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester" }));

        jLabel44.setText("Marks :");

        get_marks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_marksActionPerformed(evt);
            }
        });

        jLabel47.setText("Credit :");

        get_credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_creditActionPerformed(evt);
            }
        });

        sem_alert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sem_alert.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout DepartmentLayout = new javax.swing.GroupLayout(Department);
        Department.setLayout(DepartmentLayout);
        DepartmentLayout.setHorizontalGroup(
            DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepartmentLayout.createSequentialGroup()
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DepartmentLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DepartmentLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DepartmentLayout.createSequentialGroup()
                                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dept_add_checker, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(get_dept_name, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DepartmentLayout.createSequentialGroup()
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1)))
                                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dept_add_course, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(DepartmentLayout.createSequentialGroup()
                                                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                                        .addComponent(jLabel45)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(get_dept_course, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                                        .addComponent(jLabel46)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(get_dept_course_id, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                                        .addComponent(jLabel44)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(get_marks))
                                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                                        .addComponent(jLabel47)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(get_credit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(DepartmentLayout.createSequentialGroup()
                                                .addComponent(semester_list, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(sem_alert, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(DepartmentLayout.createSequentialGroup()
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dept_course_add_checker, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(DepartmentLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel42)))
                                        .addGap(58, 58, 58))
                                    .addGroup(DepartmentLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        DepartmentLayout.setVerticalGroup(
            DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepartmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dept_add_course, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(get_dept_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(get_marks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(get_dept_course_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(get_credit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DepartmentLayout.createSequentialGroup()
                        .addComponent(dept_course_add_checker, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(semester_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(sem_alert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(DepartmentLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(get_dept_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7)
                    .addComponent(dept_add_checker, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DepartmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        jLayeredPane1.add(Department);

        search.setBackground(new java.awt.Color(190, 222, 222));

        jLabel20.setFont(new java.awt.Font("Lucida Fax", 0, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Search Or View Data");

        search_get_data.setToolTipText("Enter your your username!");

        jButton9.setText("Search");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        search_result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Department", "ID", "Blood Group", "Phone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        search_result.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(search_result);
        if (search_result.getColumnModel().getColumnCount() > 0) {
            search_result.getColumnModel().getColumn(0).setResizable(false);
            search_result.getColumnModel().getColumn(0).setPreferredWidth(150);
            search_result.getColumnModel().getColumn(1).setResizable(false);
            search_result.getColumnModel().getColumn(1).setPreferredWidth(100);
            search_result.getColumnModel().getColumn(2).setResizable(false);
            search_result.getColumnModel().getColumn(2).setPreferredWidth(60);
            search_result.getColumnModel().getColumn(3).setResizable(false);
            search_result.getColumnModel().getColumn(3).setPreferredWidth(30);
            search_result.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout searchLayout = new javax.swing.GroupLayout(search);
        search.setLayout(searchLayout);
        searchLayout.setHorizontalGroup(
            searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(search_get_data, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(searchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        searchLayout.setVerticalGroup(
            searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchLayout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_get_data, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.add(search);

        about.setBackground(new java.awt.Color(190, 222, 222));

        jLabel58.setFont(new java.awt.Font("Lucida Fax", 0, 36)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(102, 102, 102));
        jLabel58.setText("Welcome to Dashboard !");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel59.setText("Project Name: Student Information System");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel60.setText("Instructed By: ");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel61.setText("Lecturer, Dept of CSE");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel62.setText("Linta Islam");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel63.setText("Jagannath University");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel64.setText("Team Members:");

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel65.setText("Md Helal Uddin");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel66.setText("Tamim Mahmud");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel67.setText("Mustafizur Rahman Himel");

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel68.setText("Billal Ahmed Akash");

        javax.swing.GroupLayout aboutLayout = new javax.swing.GroupLayout(about);
        about.setLayout(aboutLayout);
        aboutLayout.setHorizontalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutLayout.createSequentialGroup()
                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel58))
                    .addGroup(aboutLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(aboutLayout.createSequentialGroup()
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutLayout.createSequentialGroup()
                        .addGap(0, 88, Short.MAX_VALUE)
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addComponent(jLabel68)
                            .addComponent(jLabel67)
                            .addComponent(jLabel65))
                        .addGap(115, 115, 115)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        aboutLayout.setVerticalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel65)
                .addGap(23, 23, 23))
        );

        jLayeredPane1.add(about);

        javax.swing.GroupLayout layerLayout = new javax.swing.GroupLayout(layer);
        layer.setLayout(layerLayout);
        layerLayout.setHorizontalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
        layerLayout.setVerticalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerLayout.createSequentialGroup()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );

        getContentPane().add(layer, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 760, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
    
    
    private void DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardActionPerformed
        switchPanel(dashboard);
    }//GEN-LAST:event_DashboardActionPerformed

    private void log_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_inActionPerformed
        switchPanel(lOG_IN);
        
    }//GEN-LAST:event_log_inActionPerformed

    private void sign_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sign_inActionPerformed
        String user=uf.getText();
        String passf=pf.getText();
        try {
            String url="jdbc:mysql://127.0.0.1/sis?";
            String username="root";
            String password="";
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            java.sql.Statement stat=con.createStatement();
            String query="SELECT `password` FROM `users` WHERE username='"+user+"';";
            java.sql.ResultSet result;
            
            
            result=stat.executeQuery(query);
            result.next();
            String pass=result.getString("password");
            if(passf.equals(pass))
        {
         switchPanel(dashboard);
         show_info.setEnabled(true);
         add_information.setEnabled(true);
         search_std.setEnabled(true);
         edit_info.setEnabled(true);
         dept_info.setEnabled(true);
         aboutsw.setEnabled(true);
         log_in.setEnabled(false);
         log_out.setEnabled(true);
         
         
         uf.setText("");
         pf.setText("");
        }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        
        
        
        
    }//GEN-LAST:event_sign_inActionPerformed

    private void add_informationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_informationActionPerformed
        switchPanel(add_info);
        
    }//GEN-LAST:event_add_informationActionPerformed

    private void show_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_infoActionPerformed
        switchPanel(edit_info);
    }//GEN-LAST:event_show_infoActionPerformed
  
    
    private void log_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_outActionPerformed
        
        show_info.setEnabled(false);
        add_information.setEnabled(false);
        Department.setEnabled(false);
        search_std.setEnabled(false);
        dept_info.setEnabled(false);
        aboutsw.setEnabled(true);
        edit_info.setEnabled(false);
        log_out.setEnabled(false);
        log_in.setEnabled(true);
        switchPanel(dashboard);
        
    }//GEN-LAST:event_log_outActionPerformed

    private void std_sessionlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_sessionlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_sessionlActionPerformed

    private void std_dob_monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_dob_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_dob_monthActionPerformed

    private void std_dob_yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_dob_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_dob_yearActionPerformed

    private void std_dob_dayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_dob_dayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_dob_dayActionPerformed

    private void std_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_idActionPerformed

    private void std_sessionfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_sessionfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_sessionfActionPerformed

    private void std_gender_femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_gender_femaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_gender_femaleActionPerformed

    private void std_gender_maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_gender_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_gender_maleActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String url="jdbc:mysql://127.0.0.1/sis";

        String username="root";
        String password="";
        PreparedStatement pst=null;

        String s_name=std_name.getText();
        String s_fname=std_fname.getText();
        String s_mname=std_mname.getText();
        String s_paddress=std_paddress.getText();
        String s_pradress=std_praddress.getText();
        String s_phone=std_phone.getText();
        String s_email=std_email.getText();
        String s_blood=std_blood.getText();
        String s_gender=null;
        if(std_gender_male.isSelected())
        {
            s_gender=std_gender_male.getText();
        }else if(std_gender_female.isSelected())
        {
            s_gender=std_gender_female.getText();
        }
        String s_session=std_sessionf.getText()+"/"+std_sessionl.getText();
        String s_dept=add_get_selected_dept;
        
        System.out.print(s_dept);
        String s_id=std_id.getText();
        String s_dob=std_dob_day.getText()+"/"+std_dob_month.getText()+"/"+std_dob_year.getText();

        String query="INSERT INTO `student_informations`(`full name`, `fathers name`, `mothers name`, `present address`, `perm. address`, `phone number`, `email address`, `blood group`, `gender`, `session`, `department`, `student id`, `date of birth`,`image`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            InputStream inputStream=new FileInputStream(new File(selectedimagepath));
            pst=(PreparedStatement) con.prepareStatement(query);
            pst.setString(1, s_name);
            pst.setString(2, s_fname);
            pst.setString(3, s_mname);
            pst.setString(4, s_paddress);
            pst.setString(5, s_pradress);
            pst.setString(6, s_phone);
            pst.setString(7, s_email);
            pst.setString(8, s_blood);
            pst.setString(9, s_gender);
            pst.setString(10, s_session);
            pst.setString(11, s_dept);
            pst.setString(12, s_id);
            pst.setString(13, s_dob);
            pst.setBlob(14, inputStream);
            int updateData=pst.executeUpdate();
            if(updateData>0)
            {
                add_update_checker.setText("Successfull");
                selectedimagepath=null;
            }
            java.sql.ResultSet result;
            

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void std_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_nameActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File selectedFile=chooser.getSelectedFile();
        selectedimagepath=selectedFile.getAbsolutePath();
        
        ImageIcon ii=new ImageIcon(selectedimagepath);
        Image image=ii.getImage().getScaledInstance(std_imagedisplay.getWidth(),std_imagedisplay.getHeight(),  Image.SCALE_SMOOTH);
        std_imagedisplay.setIcon(new ImageIcon(image));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                 std_name1.setText(null);
                std_fname1.setText(null);
                std_mname1.setText(null);
                std_paddress1.setText(null);
                std_praddress1.setText(null);
                std_phone1.setText(null);
                std_email1.setText(null);
                std_blood1.setText(null);
                
                std_gender_male1.setSelected(false);
                std_gender_female1.setSelected(false);
                std_sessionf1.setText(null);
               
                std_id1.setText(null);
                std_dob_day1.setText(null);
                std_imagedisplay1.setIcon(null);
                std_search_1_value.setText(null);
                add_update_checker1.setText(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void std_search_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_search_1ActionPerformed
        String url="jdbc:mysql://127.0.0.1/sis";
        String username="root";
        String password="";
        PreparedStatement pst=null;

        String query="SELECT * FROM `student_informations` WHERE `student id`='"+std_search_1_value.getText()+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            pst=(PreparedStatement) con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();

            if(rs.next())
            {
                std_name1.setText(rs.getString("full name"));
                std_fname1.setText(rs.getString("fathers name"));
                std_mname1.setText(rs.getString("mothers name"));
                std_paddress1.setText(rs.getString("present address"));
                std_praddress1.setText(rs.getString("perm. address"));
                std_phone1.setText(rs.getString("phone number"));
                std_email1.setText(rs.getString("email address"));
                std_blood1.setText(rs.getString("blood group"));
                String s_gender=rs.getString("gender");
                if("male".equals(s_gender))
                {
                    std_gender_male1.setSelected(true);
                }else if("female".equals(s_gender))
                {
                    std_gender_female1.setSelected(true);
                }
                String std_department=rs.getString("department");
                //dept selecotor
                {
                        std_dpt1.removeAllItems();
                        query="SELECT * FROM `departments` WHERE 1";
                        java.sql.ResultSet result;
                        Statement stmt=(Statement) con.createStatement();
                        result=stmt.executeQuery(query);
                        DefaultListModel model=new DefaultListModel();
                        int index=-1;
                        int sindex=0;
                        while(result.next())
                        {
                            String dept=result.getString("Department_name");
                            std_dpt1.addItem(dept);
                            index++;
                            if(dept.equals(std_department))
                            {
                                sindex=index;
                            }
                        }
                       std_dpt1.setSelectedIndex(sindex);
        
                }
                
                   
                        
                
                
                std_sessionf1.setText(rs.getString("session"));
                std_id1.setText(rs.getString("student id"));
                std_dob_day1.setText(rs.getString("date of birth"));

                //java.sql.Blob b=rs.getBlob("image");
                //File image=new File("img.png");
                //FileOutputStream fos=new FileOutputStream(image);

                byte[] imagebytes = rs.getBytes("image");
                image=getToolkit().createImage(imagebytes);
                Image img = image.getScaledInstance(std_imagedisplay1.getWidth(),std_imagedisplay1.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon icon=new ImageIcon(img);
                std_imagedisplay1.setIcon(icon);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_std_search_1ActionPerformed

    private void std_search_1_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_search_1_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_search_1_valueActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File selectedFile=chooser.getSelectedFile();
        selectedimagepath=selectedFile.getAbsolutePath();
        
        ImageIcon ii=new ImageIcon(selectedimagepath);
        Image image2=ii.getImage().getScaledInstance(std_imagedisplay1.getWidth(),std_imagedisplay1.getHeight(),  Image.SCALE_SMOOTH);
        std_imagedisplay1.setIcon(new ImageIcon(image2));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void std_dob_day1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_dob_day1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_dob_day1ActionPerformed

    private void std_id1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_id1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_id1ActionPerformed

    private void std_sessionf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_sessionf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_sessionf1ActionPerformed

    private void std_gender_female1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_gender_female1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_gender_female1ActionPerformed

    private void std_gender_male1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_gender_male1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_gender_male1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        String url="jdbc:mysql://127.0.0.1/sis";

        String username="root";
        String password="";
        // pst=null;

        String s_name1=std_name1.getText();
        String s_fname1=std_fname1.getText();
        String s_mname1=std_mname1.getText();
        String s_paddress1=std_paddress1.getText();
        String s_pradress1=std_praddress1.getText();
        String s_phone1=std_phone1.getText();
        String s_email1=std_email1.getText();
        String s_blood1=std_blood1.getText();
        String s_gender1=null;
        if(std_gender_male1.isSelected())
        {
            s_gender1=std_gender_male1.getText();
        }else if(std_gender_female1.isSelected())
        {
            s_gender1=std_gender_female1.getText();
        }
        String s_session1=std_sessionf1.getText();
       // String s_dept1=add_get_selected_dept1;
        String s_id1=std_id1.getText();
        String s_dob1=std_dob_day1.getText();
        String s_dept1=add_get_selected_dept1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            Statement stmt=(Statement) con.createStatement();
            if(selectedimagepath!=null)
            {
                String query="UPDATE `student_informations` SET `full name`=?,`fathers name`=?,`mothers name`=?,`present address`=?,`perm. address`=?,`phone number`=?,`email address`=?,`blood group`=?,`gender`=?,`session`=?,`department`=?,`student id`=?,`date of birth`=?,`image`=? WHERE 1";
                 InputStream inputStream1=new FileInputStream(new File(selectedimagepath));
                 PreparedStatement pst=(PreparedStatement) con.prepareStatement(query);
                pst.setString(1, s_name1);
                pst.setString(2, s_fname1);
                pst.setString(3, s_mname1);
                pst.setString(4, s_paddress1);
                pst.setString(5, s_pradress1);
                pst.setString(6, s_phone1);
                pst.setString(7, s_email1);
                pst.setString(8, s_blood1);
                pst.setString(9, s_gender1);
                pst.setString(10, s_session1);
                pst.setString(11, s_dept1);
                pst.setString(12, s_id1);
                pst.setString(13, s_dob1);
                pst.setBlob(14, inputStream1);
                int updateData=pst.executeUpdate();
                if(updateData>0)
                {
                    add_update_checker1.setText("Successfull");
                }
                selectedimagepath=null;
            }
            else{
                String query="UPDATE `student_informations` SET `full name`='"+s_name1+"',`fathers name`='"+s_fname1+"',`mothers name`='"+s_mname1+"',`present address`='"+s_paddress1+"',`perm. address`='"+s_pradress1+"',`phone number`='"+s_phone1+"',`email address`='"+s_email1+"',`blood group`='"+s_blood1+"',`gender`='"+s_gender1+"',`session`='"+s_session1+"',`department`='"+s_dept1+"',`student id`='"+s_id1+"',`date of birth`='"+s_dob1+"' WHERE 1";
                int updateData=stmt.executeUpdate(query);
                add_update_checker1.setText("Successfull");
                java.sql.ResultSet result;
            }
            
            

        } catch (ClassNotFoundException | SQLException ex) {
            add_update_checker1.setText("Failed");
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void std_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_std_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_std_name1ActionPerformed

    private void search_stdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_stdActionPerformed
        switchPanel(search);
    }//GEN-LAST:event_search_stdActionPerformed

    private void dept_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dept_infoActionPerformed
        switchPanel(Department);
        
    }//GEN-LAST:event_dept_infoActionPerformed

    private void get_creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_creditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_creditActionPerformed

    private void get_marksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_marksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_marksActionPerformed

    private void get_dept_course_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_dept_course_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_dept_course_idActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String url="jdbc:mysql://127.0.0.1/sis";
        String username="root";
        String password="";
        PreparedStatement pst=null;
        String course_name=get_dept_course.getText();
        String course_id=get_dept_course_id.getText();
        String course_marks=get_marks.getText();
        String course_credit=get_credit.getText();
        String semester=(String) semester_list.getSelectedItem();
        String query="INSERT INTO `courses`(`course name`, `course id`, `Semester`, `department`, `marks`, `credit`) VALUES ('"+course_name+"','"+course_id+"','"+semester+"','"+get_selected_dept+"','"+course_marks+"','"+course_credit+"')";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            Statement stmt=(Statement) con.createStatement();
            int check=stmt.executeUpdate(query);
            dept_course_add_checker.setText("added");
            get_dept_course.setText(null);
            get_dept_course_id.setText(null);
            get_marks.setText(null);
            get_credit.setText(null);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
            dept_course_add_checker.setText("failed");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void get_dept_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_dept_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_dept_courseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String url="jdbc:mysql://127.0.0.1/sis";
        String username="root";
        String password="";
        PreparedStatement pst=null;

        String query="SELECT * FROM `departments` WHERE 1";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            java.sql.ResultSet result;
            Statement stmt=(Statement) con.createStatement();
            result=stmt.executeQuery(query);
            DefaultListModel model=new DefaultListModel();
            while(result.next())
            {
                String dept=result.getString("Department_name");
                model.addElement(dept);
                dept_list.setModel(model);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dept_listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_dept_listValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_dept_listValueChanged

    private void dept_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dept_listMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dept_listMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
       
        
        String url="jdbc:mysql://127.0.0.1/sis";
        String username="root";
        String password="";
        PreparedStatement pst=null;
        String dept_name=get_dept_name.getText();
        String query="INSERT INTO `departments`(`Department_name`) VALUES ('"+dept_name+"')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            Statement stmt=(Statement) con.createStatement();
            int check=stmt.executeUpdate(query);
            dept_add_checker.setText("added");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
            dept_add_checker.setText("failed");
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void get_dept_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_dept_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_get_dept_nameActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
         DefaultTableModel model = (DefaultTableModel) search_result.getModel();
        ((DefaultTableModel)search_result.getModel()).setRowCount(0);
        
        
        String url="jdbc:mysql://127.0.0.1/sis";
        String username="root";
        String password="";
        PreparedStatement pst=null;
        String search_data=search_get_data.getText();
        
        String query="SELECT * FROM `student_informations` WHERE `full name` LIKE  '%"+search_data+"%' OR `fathers name`='%"+search_data+"%' OR `mothers name`='%"+search_data+"%' OR `present address`='%"+search_data+"%' OR `perm. address`='%"+search_data+"%' OR `phone number`='"+search_data+"' OR `email address`='%"+search_data+"%' OR `blood group`='"+search_data+"' OR `gender`='"+search_data+"' OR `session`='"+search_data+"' OR `department`='%"+search_data+"%' OR `student id`='"+search_data+"' OR `date of birth`='"+search_data+"' ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection(url,username,password);
            java.sql.ResultSet result;
            Statement stmt=(Statement) con.createStatement();
            result=stmt.executeQuery(query);

            while(result.next())
            {
                String sr_name=result.getString("full name");
                String sr_dept=result.getString("department");
                String sr_id=result.getString("student id");
                String sr_bg=result.getString("blood group");	
                String sr_phone=result.getString("phone number");	

                model.addRow(new Object[]{sr_name, sr_dept,sr_id,sr_bg,sr_phone});
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(mainf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void aboutswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutswActionPerformed
        switchPanel(about);
    }//GEN-LAST:event_aboutswActionPerformed
    
    public  void switchPanel(JPanel panel)
    {
        jLayeredPane1.removeAll();
        jLayeredPane1.add(panel);
        
    
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dashboard;
    private javax.swing.JPanel Department;
    private javax.swing.JPanel about;
    private javax.swing.JButton aboutsw;
    private javax.swing.JLabel add_dept_name_check;
    private javax.swing.JPanel add_info;
    private javax.swing.JButton add_information;
    private javax.swing.JLabel add_update_checker;
    private javax.swing.JLabel add_update_checker1;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel dept_add_checker;
    private javax.swing.JLabel dept_add_course;
    private javax.swing.JLabel dept_course_add_checker;
    private javax.swing.JTable dept_course_table;
    private javax.swing.JButton dept_info;
    private javax.swing.JList<String> dept_list;
    private javax.swing.JPanel edit_info;
    private javax.swing.JTextField get_credit;
    private javax.swing.JTextField get_dept_course;
    private javax.swing.JTextField get_dept_course_id;
    private javax.swing.JTextField get_dept_name;
    private javax.swing.JTextField get_marks;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jnuicon;
    private javax.swing.JPanel lOG_IN;
    private javax.swing.JPanel layer;
    private javax.swing.JButton log_in;
    private javax.swing.JButton log_out;
    private javax.swing.JLabel mam;
    private javax.swing.JPasswordField pf;
    private javax.swing.JPanel search;
    private javax.swing.JTextField search_get_data;
    private javax.swing.JTable search_result;
    private javax.swing.JButton search_std;
    private javax.swing.JLabel sem_alert;
    private javax.swing.JComboBox<String> semester_list;
    private javax.swing.JButton show_info;
    private javax.swing.JButton sign_in;
    private javax.swing.JTextField std_blood;
    private javax.swing.JTextField std_blood1;
    private javax.swing.JTextField std_dob_day;
    private javax.swing.JTextField std_dob_day1;
    private javax.swing.JTextField std_dob_month;
    private javax.swing.JTextField std_dob_year;
    private javax.swing.JComboBox<String> std_dpt;
    private javax.swing.JComboBox<String> std_dpt1;
    private javax.swing.JTextField std_email;
    private javax.swing.JTextField std_email1;
    private javax.swing.JTextField std_fname;
    private javax.swing.JTextField std_fname1;
    private javax.swing.ButtonGroup std_gender;
    private javax.swing.JRadioButton std_gender_female;
    private javax.swing.JRadioButton std_gender_female1;
    private javax.swing.JRadioButton std_gender_male;
    private javax.swing.JRadioButton std_gender_male1;
    private javax.swing.JTextField std_id;
    private javax.swing.JTextField std_id1;
    private javax.swing.JLabel std_imagedisplay;
    private javax.swing.JLabel std_imagedisplay1;
    private javax.swing.JTextField std_mname;
    private javax.swing.JTextField std_mname1;
    private javax.swing.JTextField std_name;
    private javax.swing.JTextField std_name1;
    private javax.swing.JTextField std_paddress;
    private javax.swing.JTextField std_paddress1;
    private javax.swing.JTextField std_phone;
    private javax.swing.JTextField std_phone1;
    private javax.swing.JTextField std_praddress;
    private javax.swing.JTextField std_praddress1;
    private javax.swing.JButton std_search_1;
    private javax.swing.JTextField std_search_1_value;
    private javax.swing.JTextField std_sessionf;
    private javax.swing.JTextField std_sessionf1;
    private javax.swing.JTextField std_sessionl;
    private javax.swing.JTextField uf;
    // End of variables declaration//GEN-END:variables
}
