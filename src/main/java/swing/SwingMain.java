package swing;

import com.essa.framework.*;
import com.essa.pageObject.GoodsManage.AddOriginalGoodsPage;
import com.essa.pageObject.GoodsManage.UpdatePicPage;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.testng.TestNG;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator 图形化
 */
public class SwingMain {

	private JFrame frmvBy;
	private JTextField supplierName;
	private JTextField buyerNo2;
	public static String no;
	private JTextField account;
	private JTextArea SkuNo;
	private JTextField password;
	private JTextField registerAccount;
	private JTextArea ja;
	private JTextField po;
	
	private String buyerAccount;//采购商账号，存储于配置文件中，记录上一次使用的账号
	private String supplier;//供应商
	private String buyerNo;//采购商编号

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingMain window = new SwingMain();
					window.frmvBy.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingMain() {
		
		initData();
		initialize();
	}
	
	/**
	 * 读取data.xml文件保存的用户使用的数据，用于读取该用户上一次输入的数据
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void initData() {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File(SystemConstant.RESOURCE_PATH + "\\data.xml"));
			Element root = document.getRootElement();
			Element element = root.getChild("data");
			this.buyerAccount = element.getChildText("buyerAccount");
			this.supplier = element.getChildText("supplier");
			this.buyerNo = element.getChildText("buyerNo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将用户输入过的数据保存在data.xml文件中
	 * @param key 字段名
	 * @param value 参数值
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void setData(String key,String value) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(new File(SystemConstant.RESOURCE_PATH + "\\data.xml"));
			Element root = document.getRootElement();
			Element element = root.getChild("data");
			element.getChild(key).setText(value);
			XMLOutputter XMLOut = new XMLOutputter();
			XMLOut.output(document,new FileOutputStream(SystemConstant.RESOURCE_PATH + "\\data.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ");
		Date today = new Date();
		String time = format.format(today);
		return time;
	}

	/**
	 * frame初始化.
	 */
	private void initialize() {
		frmvBy = new JFrame("ESSA自动化测试工具v1.0.0");
		frmvBy.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmvBy.setTitle("ESSA自动化测试工具v1.0.1");
		frmvBy.getContentPane().setEnabled(false);
		frmvBy.setResizable(false);
		frmvBy.setBackground(UIManager.getColor("Button.background"));
		frmvBy.setForeground(Color.LIGHT_GRAY);
		frmvBy.setBounds(650, 350, 526, 467);
		frmvBy.setSize(686, 405);
		frmvBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmvBy.getContentPane().setLayout(null);

		//日志
		JScrollPane jsp;
		ja = new JTextArea();
		jsp = new JScrollPane(ja);
		jsp.setBounds(22, 186, 631, 134);
		frmvBy.getContentPane().add(jsp);

		ja.setEditable(false);
		ja.setWrapStyleWord(true);
		ja.setLineWrap(true);
		ja.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		ja.append(getCurrentTime() + "默认选择场景：新增原厂商品\r\n");

		//操作环境选择
		JLabel label = new JLabel("操作环境：");
		label.setBounds(24, 53, 78, 15);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		frmvBy.getContentPane().add(label);

		//加入到购物车场景
		final JPanel ToCart = new JPanel();
		ToCart.setBounds(231, 42, 230, 128);
		ToCart.setVisible(false);
		frmvBy.getContentPane().add(ToCart);
		ToCart.setLayout(null);

		//可视化操作选项
		JLabel label_1 = new JLabel("可视化操作：");
		label_1.setBounds(10, 119, 88, 15);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		frmvBy.getContentPane().add(label_1);

		//可视化
		JRadioButton view = new JRadioButton("是");
		view.setBounds(103, 115, 42, 23);
		view.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		view.setSelected(true);
		frmvBy.getContentPane().add(view);

		//无头浏览器
		JRadioButton notView = new JRadioButton("否");
		notView.setBounds(160, 115, 44, 23);
		notView.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		notView.setEnabled(false);
		frmvBy.getContentPane().add(notView);

		ButtonGroup group = new ButtonGroup();
		group.add(view);
		group.add(notView);

		//po询价场景
		final JPanel POInquiry = new JPanel();
		POInquiry.setBounds(231, 42, 234, 134);
		frmvBy.getContentPane().add(POInquiry);
		POInquiry.setVisible(false);
		POInquiry.setLayout(null);

		// po单号字段
		JLabel POnum = new JLabel("PO单号：");
		POnum.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		POnum.setBounds(25, 5, 68, 25);
		POInquiry.add(POnum);


		// po单号文本值
		po = new JTextField();
		po.setBounds(90, 6, 110, 25);
		POInquiry.add(po);
		po.setColumns(10);
		
		//必填文本
		JLabel lblNewLabel = new JLabel("*必填");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(200, 10, 44, 15);
		POInquiry.add(lblNewLabel);

		//右侧流程介绍文本
		final JLabel process = new JLabel();
		process.setBounds(474, 25, 179, 170);
		process.setForeground(Color.GRAY);
		process.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		process.setText(
				"<html><body>新增原厂商品场景流程:<br>1.商品建档<br>2.更新商品图片<br>3.审核商品<br>4.检查商品库，验证是否新增成功<br>PS：以上均由账号（linxun）操作<br><br></body></html>");
		frmvBy.getContentPane().add(process);

		//操作场景
		JLabel label_2 = new JLabel("操作场景：");
		label_2.setBounds(24, 87, 78, 15);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		frmvBy.getContentPane().add(label_2);

		//新增原厂商品场景
		final JPanel addOriginal = new JPanel();
		addOriginal.setBounds(231, 37, 234, 134);
		addOriginal.setVisible(true);
		frmvBy.getContentPane().add(addOriginal);
		addOriginal.setLayout(null);

		//场景选择，插入对应的值，并打印在日志上
		final JComboBox selectSystem = new JComboBox<String>();
		selectSystem.setBounds(102, 51, 114, 20);
		selectSystem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		for (EnvEnum envEnum : EnvEnum.values()) {
			selectSystem.addItem(envEnum.getCode());
		}
		frmvBy.getContentPane().add(selectSystem);

		//添加市场商品
		final JPanel addMarket = new JPanel();
		addMarket.setBounds(0, 67, 230, 46);
		addOriginal.add(addMarket);
		addMarket.setVisible(false);
		addMarket.setLayout(null);
		
		//采购商注册
		final JPanel register = new JPanel();
		register.setBounds(231, 42, 224, 134);
		frmvBy.getContentPane().add(register);

		//浏览器选择
		final JComboBox browser = new JComboBox();
		browser.setEnabled(false);
		browser.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		browser.setBounds(102, 147, 114, 20);
		browser.setModel(new DefaultComboBoxModel(new String[] { "Chrome", "Firefox", "IE" }));
		frmvBy.getContentPane().add(browser);

		//选择图片
		JButton button = new JButton("选择图片");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBounds(91, 44, 91, 23);
		addOriginal.add(button);

		//必填
		final JLabel notnull = new JLabel("*必填");
		notnull.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		notnull.setBounds(192, 48, 42, 15);
		addOriginal.add(notnull);
		notnull.setForeground(Color.RED);
		
		//场景选择，根据随选场景做对应的交互
		final JComboBox selectScene = new JComboBox();
		selectScene.setBounds(102, 85, 114, 20);
		selectScene.setForeground(Color.BLACK);
		selectScene.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		selectScene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//此处为默认将所有的场景视图先隐藏，后续根据所选场景显示对应的视图
				addOriginal.setVisible(false);
				addMarket.setVisible(false);
				ToCart.setVisible(false);
				POInquiry.setVisible(false);
				register.setVisible(false);
				addOriginal.add(button);
				addOriginal.add(notnull);
				button.setBounds(91, 44, 91, 23);
				notnull.setBounds(192, 48, 42, 15);
//				loadFinish.setVisible(false);
				switch ((String)selectScene.getSelectedItem()){
					default:
						process.setText(
								"<html><body>新增原厂商品场景流程:<br>1.商品建档<br>2.更新商品图片<br>3.审核商品<br>4.检查商品库，验证是否新增成功<br>PS：以上均由账号（linxun）操作<br><br></body></html>");
						addOriginal.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：新增原厂商品\r\n");
						break;
					case "新增市场商品":
						process.setText("<html><body>新增市场商品场景流程：<br>" + "1.指定采购商编号(默认:RUS00833)<br>"
								+ "2.使用账号（maomeixiang)对指定采购商进行市场商品建档并选择审核人为\"邢昌勇\"<br>"
								+ "3.使用账号（xingchangyong）对市场商品审核<br></body></html>");
						addOriginal.setVisible(true);
						addMarket.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：新增市场商品\r\n");
						break;
					case "发布团购":
						ja.append(getCurrentTime() + "已选择场景：发布团购，运行过程中将会打开关闭浏览器2次！\r\n");
						process.setText(
								"<html><body>发布团购场景流程：<br><font color=\"red\">1.先执行新增原厂商品流程，生成一个原厂SKU</font><br>2.使用账号（linrong）为新增的SKU分配类目经理<br>3.团购设置中添加该SKU，并发布团购，验证团购发布结果</body></html>");
						addOriginal.setVisible(true);
						break;
					case "采购商注册":
						register.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：采购商注册，运行过程中将会打开关闭浏览器2次！\r\n");
						process.setText(
								"<html><body>采购商注册流程：<br>1.使用账号（admin）在bpms后台生成一个邀请码<br>2.采购商平台填写注册信息，并填入上一步生成的邀请码<br>PS:新账号密码默认为：essa123<br><br><br></body></html>");
						break;
					case "成品询价":
						ToCart.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：成品询价，将重启浏览器多次\r\n");
						process.setText(
								"<html><body>成品询价流程：<br>1.Buyer平台根据填入的采购<br>商信息加入sku，若未填写sku<br>编号，将随机从数据库获取符<br>合要求的sku<br>2.bpms后台登录账号（chenhong）对该sku进行成<br>品询价审核<br></body></html>");
						break;
					case "生成PO":
						ToCart.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：生成PO，将重启浏览器多次\r\n");
						process.setText(
								"<html><body>生成PO流程：<br>1.采购商平台加入商品至购物车<br>2.bpms后台登录账号（chenhong）对该sku进行成品询价审核<br>3.再次登录采购商平台，进行拼柜、设置唛头、贴纸和提交PO操作</body></html>");
						break;
					case "PO询价":
						POInquiry.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：PO询价\r\n");
						process.setText("<html><body>PO询价使用介绍：<br>1.填入需要询价的PO单号<br>2.工具将对该PO单号循环进行询价，直到所有商品均询价完成<br>3.如果运行过程中出现询价异常等弹框，手工关闭弹窗后脚本仍会继续执行之后的操作</body></html>");
						break;
					case "新增开发商品":
						addOriginal.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：新增开发商品\r\n");
						break;
					case "装柜完成":
						process.setText(
								"<html><body>装柜流程：<br>1.添加商品到购物车<br>2.询价并拼柜生成PO<br>3.发单、制单<br>4.仓储管理、船务管理至装柜完成<br><br><br></body></html>");
						button.setBounds(0, 120, 91, 23);
						notnull.setBounds(100, 125, 42, 15);
						process.add(button);
						process.add(notnull);
						ToCart.setVisible(true);
						ja.append(getCurrentTime() + "已选择场景：装柜完成，将重启浏览器多次\r\n");
						break;
				}
			}
		});
		selectScene.setModel(
				new DefaultComboBoxModel(new String[] { "新增原厂商品", "新增市场商品","新增开发商品","装柜完成", "发布团购", "采购商注册", "成品询价", "生成PO", "PO询价" }));
		frmvBy.getContentPane().add(selectScene);

		JLabel label_3 = new JLabel("供应商名称：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_3.setBounds(9, 15, 91, 15);
		addOriginal.add(label_3);

		//供应商名称
		supplierName = new JTextField();
		supplierName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		supplierName.setForeground(Color.BLACK);
		supplierName.setBounds(92, 10, 114, 25);
		addOriginal.add(supplierName);
		supplierName.setText(supplier);
		supplierName.setColumns(10);


		JLabel lblSku = new JLabel("商品图片：");
		lblSku.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblSku.setBounds(22, 48, 70, 15);
		addOriginal.add(lblSku);



		//图片地址
		final JLabel picPath = new JLabel("");
		picPath.setVisible(false);
		addOriginal.add(picPath);


		JLabel label_5 = new JLabel("采购商编号：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_5.setBounds(8, 15, 86, 15);
		addMarket.add(label_5);

		//采购商编号
		buyerNo2 = new JTextField();
		buyerNo2.setBounds(93, 11, 114, 25);
		addMarket.add(buyerNo2);
		buyerNo2.setFont(new Font("宋体", Font.PLAIN, 14));
		buyerNo2.setText(buyerNo);
		buyerNo2.setColumns(10);

		//选择图片按钮
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jf.showDialog(new JLabel(), "选择图片");
				File file = jf.getSelectedFile();
				String s = null;
				try {
					s = file.getAbsolutePath();
				} catch (NullPointerException exception) {
				}
				if (s == "" || s == null) {
					ja.append(getCurrentTime() + "您未选择图片，请选择图片\r\n");
				} else {
					ja.append(getCurrentTime() + "选择的图片为：" + s + "\r\n");
					picPath.setText(s);
					notnull.setText("已选");
				}
			}
		});

		JLabel label_4 = new JLabel("浏览器：");
		label_4.setBounds(38, 149, 62, 15);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		frmvBy.getContentPane().add(label_4);

		//开始按钮
		JButton btnNewButton = new JButton("开 始");
		btnNewButton.setBounds(551, 325, 101, 35);
		frmvBy.getContentPane().add(btnNewButton);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 17));

		JLabel lblEssav = new JLabel("ESSA自动化测试工具 V1.0.1");
		lblEssav.setBounds(225, 10, 228, 29);
		lblEssav.setFont(new Font("微软雅黑", Font.BOLD, 16));
		frmvBy.getContentPane().add(lblEssav);

		JLabel lblwindowsjdkbug = new JLabel(
				"<html><body>提示：1.建议使用版本号为68.0的Chrome浏览器<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.由于部分操作会用到鼠标事件，若运行时手动切换界面可能会导致运行出错</body></html>");
		lblwindowsjdkbug.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblwindowsjdkbug.setBounds(25, 323, 456, 40);
		frmvBy.getContentPane().add(lblwindowsjdkbug);

		JLabel Account = new JLabel("采购商账号：");
		Account.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		Account.setBounds(9, 10, 88, 15);
		ToCart.add(Account);

		JLabel Password = new JLabel("密码：");
		Password.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		Password.setBounds(50, 41, 65, 15);
		ToCart.add(Password);

		JLabel No = new JLabel("商品编号：");
		No.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		No.setBounds(22, 100, 88, 15);
		ToCart.add(No);

		account = new JTextField();
		account.setText(buyerAccount);
		account.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		account.setBounds(92, 4, 130, 25);
		ToCart.add(account);
		account.setColumns(10);

		password = new JPasswordField();
		password.setText("essa123");
		password.setToolTipText("");
		password.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		password.setBounds(92, 37, 130, 25);
		ToCart.add(password);
		password.setColumns(10);

		//sku编号
		SkuNo = new JTextArea();
		SkuNo.setText("选填，勿填活动商品");
		SkuNo.setForeground(Color.LIGHT_GRAY);
		SkuNo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		SkuNo.setLineWrap(true);
		SkuNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SkuNo.getText().equals("选填，勿填活动商品") || SkuNo.getText().equals("选填，勿填非活动商品")  ) {
					SkuNo.setForeground(Color.black);
					SkuNo.setText("");
				}
			}
		});
		SkuNo.setBounds(92, 100, 130, 25);
		SkuNo.setBorder(new LineBorder(new Color(127,157,185), 1, false));
		ToCart.add(SkuNo);
		SkuNo.setColumns(10);
		
		//商品类型
		JLabel kind = new JLabel("商品类型：");
		kind.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		kind.setBounds(22, 75, 70, 15);
		ToCart.add(kind);
		
		//非活动商品
		JRadioButton notActivity = new JRadioButton("非活动");
		notActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Model.setIsactivity(0);
				SkuNo.setText("选填，勿填活动商品");
			}
		});
		notActivity.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		notActivity.setBounds(92, 71, 70, 23);
		ToCart.add(notActivity);
		
		//活动商品
		JRadioButton isActivity = new JRadioButton("活动");
		isActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model.setIsactivity(1);
				SkuNo.setText("选填，勿填非活动商品");
			}
		});
		isActivity.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		isActivity.setBounds(165, 71, 64, 23);
		ToCart.add(isActivity);
		
		//活动和非活动单选
		ButtonGroup group1 = new ButtonGroup();
		group1.add(notActivity);
		group1.add(isActivity);
		notActivity.setSelected(true);

		JLabel RegisterAccount = new JLabel("采购商邮箱：");
		register.add(RegisterAccount);
		RegisterAccount.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		registerAccount = new JTextField();
		registerAccount.setForeground(Color.LIGHT_GRAY);
		registerAccount.setText("若不填写将自动生成");
		registerAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (registerAccount.getText().equals("若不填写将自动生成")) {
					registerAccount.setForeground(Color.black);
					registerAccount.setText("");
				}
			}
		});
		register.add(registerAccount);
		registerAccount.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		registerAccount.setColumns(10);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {// 加入线程

					@Override
					protected Void doInBackground() throws Exception {
						String Environment = (String) selectSystem.getSelectedItem();
						String Scene = (String) selectScene.getSelectedItem();
						String Browser = (String) browser.getSelectedItem();
						//记录用户输入的数据，下次读取
						setData("supplier", supplierName.getText());
						setData("buyerNo", buyerNo2.getText());
						setData("buyerAccount", account.getText());
						ja.append(getCurrentTime() + "程序正在启动中……切勿双击【开始】\r\n");

						Model.setBuyerNo(buyerNo2.getText());
						Model.setPicPath(picPath.getText());
						Model.setSupplierName(supplierName.getText());
						BrowserEngine.setInit(Environment, Browser);
						if (!(registerAccount.getText().equals("")
								|| registerAccount.getText().equals("若不填写将自动生成"))) {
							Model.setEmail(registerAccount.getText());
						}
						Model.setBuyerAccount(account.getText());
						Model.setBuyerPassword(password.getText());
						Model.setSkuNo(SkuNo.getText());
						Model.setPoNum(po.getText());

						TestNG testNG = new TestNG();
						List<String> suites = new ArrayList<String>();
						switch (Scene){

							case "新增市场商品":
								suites.add(SuitesEnum.ADD_MARKET_GOODS.getSuiteName());
								break;
							case "发布团购":
								AddOriginalGoodsPage.setSupplierName(supplierName.getText());
								suites.add(SuitesEnum.PUBLISH_GROUP_PURCHASE.getSuiteName());
								break;
							case "采购商注册":
								suites.add(SuitesEnum.BUYER_REGISTER.getSuiteName());
								break;
							case "成品询价":
								if (Model.getIsactivity() == 1) {
									suites.add(SuitesEnum.ACTIVITY_INQUIRY.getSuiteName());
								}else {
									suites.add(SuitesEnum.PRODUCT_INQUIRY.getSuiteName());
								}
								break;
							case "生成PO":
								if (Model.getIsactivity() == 1) {
									suites.add(SuitesEnum.ACTIVITY_SEND_PO.getSuiteName());
								}else {
									suites.add(SuitesEnum.SEND_PO.getSuiteName());
								}
								break;
							case "PO询价":
								suites.add(SuitesEnum.PO_INQUIRY.getSuiteName());
								break;
							default : //默认新增原厂商品
								AddOriginalGoodsPage.setSupplierName(supplierName.getText());
								suites.add(SuitesEnum.ADD_ORIGINAL_GOODS.getSuiteName());
								break;
							case "新增开发商品":
								suites.add(SuitesEnum.ADD_DEV_GOODS.getSuiteName());
								break;
							case "装柜完成":
								suites.add(SuitesEnum.LOAD_FINISH.getSuiteName());
								break;
						}
						testNG.setTestSuites(suites);
						testNG.run();
						ja.append(getCurrentTime() + "=======" + Scene + "场景,执行完毕！=======\r\n");
						switch (Scene){
							default: //默认新增原厂商品
							case "新增市场商品":
							case "新增开发商品":
							case "发布团购":
								no = Model.getSkuNo();
								if (no != null) {
									ja.append(getCurrentTime() + "SKU商品编号：" + no + "\r\n");
								} else {
									ja.append(getCurrentTime()
											+ "程序执行失败了！\r\n");
								}
								break;
							case "采购商注册":
								String code = Model.getInvateCode();
								String email = Model.getEmail();
								if (code != null && email != null) {
									ja.append(getCurrentTime() + "生成的邀请码：" + Model.getInvateCode() + "\r\n");
									ja.append(getCurrentTime() + "新采购商邮箱：" + Model.getEmail() + "\r\n");
								} else {
									ja.append(getCurrentTime()
											+ "程序执行失败了！\r\n");
								}
								break;
							case "成品询价":
								ja.append(getCurrentTime() + "采购商账号：" + Model.getBuyerAccount() + "\r\n");
								ja.append(getCurrentTime() + "SKU编号：" + Model.getSkuNo() + "\r\n");
								break;
							case "生成PO":
								ja.append(getCurrentTime() + "采购商账号：" + Model.getBuyerAccount() + "\r\n");
								ja.append(getCurrentTime() + "PO单号：" + Model.getPoNum() + "\r\n");
								break;
							case "装柜完成":
								ja.append(getCurrentTime() + "PO单号：" + Model.getPoNum() + "\r\n");
								break;
						}
						return null;
					}
				};
				worker.execute();

			}
		});

	}
}
