package com.levelup.stock.view;

//import com.dropbox.core.DbxException;
import com.dropbox.core.DbxException;
import com.levelup.spring.dao.DealRepository;
import com.levelup.spring.dao.ParseCSV;
import com.levelup.spring.dao.impl.DealOpenCSVImpl;
import com.levelup.spring.service.DealService;
//import com.levelup.spring.service.DropBoxService;
import com.levelup.spring.service.DropBoxService;
import com.levelup.spring.service.UserService;
//import com.levelup.spring.service.impl.DropBoxServiceImpl;
import com.levelup.spring.service.impl.DropBoxServiceImpl;
import com.levelup.stock.model.Deal;
import com.levelup.stock.model.DropBoxFile;
import com.levelup.stock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DealService dealService;

    @Autowired
    DealRepository dealRepository;

    @Autowired
    DropBoxService dropBoxService = new DropBoxServiceImpl();


    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    public String checkUser(Model model, @RequestParam String email) {

        User user = new User();
        user.setEmail(email);

        if (userService.checkUserByEmail(user)) {

            return "messageRegister.page";
        }
        return "null.page";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("user") User user,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String name) {
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        if (userService.checkUserByEmail(user)) {

            return "messageRegister.page";

        } else {
            userService.create(user);

//            ParseJacksonCSV parseJacksonCSV = new DealOpenCSVImpl();
//            List<Deal> deals = parseJacksonCSV.parse("c://orders-example.csv");
//            for (Deal deal : deals) {
//                deal.setUserId(user.getId());
//                dealService.create(deal);
//            }


//            ParseCSVImpl myParse = new ParseCSVImpl();
//            ArrayList<ArrayList<String>> pars = myParse.parseCSV("c://orders-example.csv");
//            for (int i = 0; i < pars.size(); i++) {
//                Deal deal = new Deal();
//                int j = -1;
//                deal.setType(pars.get(i).get(++j));
//                deal.setTicket(pars.get(i).get(++j));
//                deal.setSymbol(pars.get(i).get(++j));
//                deal.setLots(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setBuySell(pars.get(i).get(++j));
//                deal.setOpenPrice(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setClosePrice(Double.parseDouble(pars.get(i).get(++j)));
//
//                DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                Date date = new Date();
//                try {
//                     date = format.parse(pars.get(i).get(++j));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    date = format.parse(pars.get(i).get(++j));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                deal.setOpenTime(date);
//                deal.setCloseTime(date);
//                deal.setProfit(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setSwap(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setCommission(pars.get(i).get(++j));
//                deal.setTp(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setSl(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setPips(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setResult(pars.get(i).get(++j));
//                deal.setTradeDuration(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setMagicNumber(pars.get(i).get(++j));
//                deal.setOrderComment(pars.get(i).get(++j));
//                deal.setMae(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setMfe(Double.parseDouble(pars.get(i).get(++j)));
//                deal.setfXBlueLiveAccount(pars.get(i).get(++j));
//                deal.setUserId(user.getId());
//                dealService.create(deal);
//            }

            return "null.page";
        }
    }


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signInUser(Model model, @ModelAttribute("user") User user,
                             @RequestParam String email,
                             @RequestParam String password) {

        User user2 = userService.getUserByEmailAndPassword(email, password);
        if (user2 != null) {
            user.setUser(user2);
            dealRepository.getAllUniqe(email,100000L,190000000L);
            return "null.page";
        } else {
            return "messageLogin.page";
        }
    }

    @RequestMapping(value = "/dropBox", method = RequestMethod.GET)
    public String getDropBoxFiles(Model model) {
        //System.out.println("sasasa");
        List<DropBoxFile> dropBoxFileList;
        dropBoxService.getAuth();
//        model.addAttribute("authorizeUrl", dropBoxService.getAuthorizeUrl(dropBoxService.getAppKey()
//                , dropBoxService.getAppSecret()));
        try {
            dropBoxFileList = dropBoxService.listDropboxFolders("/");
            model.addAttribute("dropBox", dropBoxFileList);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return "dropBox.page";
    }


    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public String downloadFileFromDropBox(@RequestParam String nameFile)  {

//        System.out.println("sasasa");
//        System.out.println(nameFile);
        try {
            dropBoxService.downloadFromDropbox(nameFile);
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "main.page";
    }



    @RequestMapping(value = "/parse", method = RequestMethod.GET)
    public String parseFile(Model model){

        String rootPath = System.getProperty("catalina.home");
        File pathName = new File(rootPath + File.separator + "tmpFiles");
//        if (!dir.exists())
//            dir.mkdirs();
        String[] serverFiles = pathName.list();
        model.addAttribute("serverFiles",serverFiles);
//        for (int i = 0; i < fileNames.length; i++)
//        {
//            File f = new File (pathName.getPath(),fileNames[i]);
//            if (f.isDirectory())
//            {
//                try {
//                    System.out.println (f.getCanonicalPath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }

//          return "";
        return "parse.page";
    }

    @RequestMapping(value = "/dealCreate", method = RequestMethod.POST)
    public String dealCreate(@RequestParam String nameFile,
                             @ModelAttribute("user") User user) {

        String rootPath = System.getProperty("catalina.home");
        File pathName = new File(rootPath + File.separator + "tmpFiles");

        File parseFile = new File(pathName.getAbsolutePath()
                + File.separator + nameFile);
        ParseCSV parseCSV = new DealOpenCSVImpl();
        List<Deal> deals = parseCSV.parse(parseFile.getAbsolutePath());

        for (Deal deal : deals) {
            deal.setUserId(user.getId());
            dealService.create(deal);
        }

        System.out.println(nameFile);


        return "";
    }

    @RequestMapping(value = "/dustTest")
    public String getDust() {
        return "dustTest.page";
    }


    @RequestMapping(value = "/dust", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User dealCreate() {
        User user = userService.getById(2L);
        return user;
    }
}
