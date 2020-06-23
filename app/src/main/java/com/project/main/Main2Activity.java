package com.project.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {

    public ListView listview;
    public Button button2;
    public EditText usersearch;
    public String search;
    ArrayList<String> titleallproducts = new ArrayList<String>();
    ArrayList<String> allproducts = new ArrayList<String>(); // all products combine
    ArrayList<String> producturl = new ArrayList<String>();
    ArrayList<String> imageurls = new ArrayList<String>();
    ArrayList<String> temparraylist;
    ArrayList<String> tempurllist;
    ArrayList<String> tempimageurl;
    ArrayList<String> tempproducttitle;
    TextView resultText;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listview = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        String input1 = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        Bundle args = intent.getBundleExtra("BUNDLE");
        temparraylist = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        tempurllist = (ArrayList<String>) args.getSerializable("URLLINKS");
        tempimageurl = (ArrayList<String>) args.getSerializable("IMAGEURL");
        tempproducttitle = (ArrayList<String>) args.getSerializable("PRODUCTTITLE");
        resultText= (TextView) findViewById(R.id.resultText);
        resultText.setText("Showing Results for: "+input1);


        if (temparraylist.isEmpty() != true) {
            MyAdaptor adaptor = new MyAdaptor(Main2Activity.this,temparraylist,tempurllist,tempproducttitle,tempimageurl);
            listview.setAdapter(adaptor);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String link = tempurllist.get(position);
                    Intent intent = new Intent((Intent.ACTION_VIEW));
                    intent.setData(Uri.parse(link));
                    startActivity(intent);
                }
            });
        }


        button2 = (Button) findViewById(R.id.btnSearch2);
        usersearch = (EditText) findViewById(R.id.searchText2);



        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                if (usersearch.getText().length() <= 0) {
                    Toast.makeText(Main2Activity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                } else {
                    final ProgressDialog pd = new ProgressDialog(Main2Activity.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    if(allproducts.size() > 0){
                        allproducts.clear();
                    }
                    if(producturl.size() >0){
                        producturl.clear();
                    }
                    if(titleallproducts.size() > 0){
                        titleallproducts.clear();
                    }
                    if(imageurls.size()>0){
                        imageurls.clear();
                    }
                    final MyAdaptor adaptor = new MyAdaptor(Main2Activity.this,allproducts,producturl,titleallproducts,imageurls);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            resultText.setText("Showing Results for: "+usersearch.getText());
                            pd.dismiss();
                            listview.setAdapter(adaptor);
                        }
                    }, 6000);

                    Thread t1 = new Thread(){
                        public void run(){
                            Flipkart flip = new Flipkart();
                            flip.execute("https://www.flipkart.com/search?q=" + usersearch.getText()+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off");
                        }
                    };

                    Thread t2 = new Thread(){
                        public void run(){
                            Paytm pyt = new Paytm();
                            pyt.execute("https://www.paytmmall.com/shop/search?q=" + usersearch.getText() + "&from=organic&child_site_id=6");
                        }
                    };

                    Thread t3 = new Thread(){
                        public void run(){
                            Snapdeal snap = new Snapdeal();
                            snap.execute("https://www.snapdeal.com/search?keyword=" + usersearch.getText() + "&santizedKeyword=&catId=&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&changeBackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy");
                        }
                    };

                    Thread t4 = new Thread() {
                        public void run(){
                            Shopclues shopclues = new Shopclues();
                            shopclues.execute("https://www.shopclues.com/search?q="+usersearch.getText()+"&sc_z=2222&z=0&count=10");
                        }
                    };
                    t1.start();
                    t2.start();
                    t3.start();
                    t4.start();
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String link = producturl.get(position);
                            Intent intent = new Intent((Intent.ACTION_VIEW));
                            intent.setData(Uri.parse(link));
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    class MyAdaptor extends ArrayAdapter<String>{
        Context context;
        ArrayList<String> aboutproduct;
        ArrayList<String> producturl;
        ArrayList<String> producttitle;
        ArrayList<String> imageurl;
        MyAdaptor(Context c, ArrayList<String> aboutproduct, ArrayList<String> producturl,ArrayList<String> producttitle, ArrayList<String> imageurl){
            super(c,R.layout.row,R.id.textView1,aboutproduct);
            this.context = c;
            this.aboutproduct = aboutproduct;
            this.producturl = producturl;
            this.producttitle = producttitle;
            this.imageurl = imageurl;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row,parent,false);
            TextView producttitleshow = row.findViewById(R.id.textView1);
            TextView aboutproductdisplay = row.findViewById(R.id.textView2);
            ImageView iv = row.findViewById(R.id.productimage);
            Context context = parent.getContext();
            try {
                if(imageurl.get(position).length() <=0){
                    Picasso.with(context).load("https://1m19tt3pztls474q6z46fnk9-wpengine.netdna-ssl.com/wp-content/themes/unbound/images/No-Image-Found-400x264.png").into(iv);
                }else {
                    Picasso.with(context).load(imageurl.get(position)).into(iv);
                }
            } catch (Exception m) {

            }
            aboutproductdisplay.setText(aboutproduct.get(position));
            producttitleshow.setText(producttitle.get(position));
            if(position == 0){
                producttitleshow.setTextSize(30);
                producttitleshow.setGravity(20);
            }
            if(position == 7){
                producttitleshow.setTextSize(30);
                producttitleshow.setGravity(20);
            }
            if(position == 14){
                producttitleshow.setTextSize(30);
                producttitleshow.setGravity(20);
            }
            if(position == 21){
                producttitleshow.setTextSize(30);
                producttitleshow.setGravity(20);
            }

            return row;
        }
    }



    private class Flipkart extends AsyncTask<String, Void, ArrayList<String>> {
        ArrayList<String> temptitlestore = new ArrayList<>();
        ArrayList<String> tempurlstore = new ArrayList<>();
        ArrayList<String> tempimageurl = new ArrayList<>();
        String link;

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            String product, urlstore,title,imagelink;
            super.onPostExecute(s);

            for (int j = 0; j < 6; j++) {
                product = s.get(j);
                title = temptitlestore.get(j);
                titleallproducts.add(title);
                urlstore = tempurlstore.get(j);
                allproducts.add(product);
                producturl.add(urlstore);
                imagelink = tempimageurl.get(j);
                imageurls.add(imagelink);
            }
            String seemore = "SHOW MORE PRODUCTS ON FLIPKART......";
            String seemoreimage = "https://previews.123rf.com/images/brisker/brisker1605/brisker160500027/58489983-16-icons-of-different-products-categories-for-online-shops.jpg";
            allproducts.add("");
            titleallproducts.add(seemore);
            producturl.add(link);
            imageurls.add(seemoreimage);


        }

        @Override
        protected ArrayList<String> doInBackground(final String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements links = doc.getElementsByClass("_3O0U0u");
                Elements links1 = doc.getElementsByClass("_3liAhj");
                Elements fashions = doc.getElementsByClass("IIdQZO _1SSAGr");
                Elements maskssans = doc.getElementsByClass("_3liAhj");
                ArrayList<String> mainlist = new ArrayList<String>();
                link = strings[0];
                String title = "FLIPKART PRODUCTS";
                String url = "https://www.flipkart.com";
                String imageurl = "https://images.news18.com/ibnlive/uploads/2019/08/flipkart.jpg";
                temptitlestore.add(title);
                tempurlstore.add(url);
                tempimageurl.add(imageurl);
                mainlist.add("");

                for (Element testlink1 : links) {
                    String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                    String permanent1 = null;

                    Elements eltitle1 = testlink1.getElementsByClass("_3wU53n");

                    if (eltitle1.size() > 0) {
                        for (Element link : links) {

                            Elements elLink = link.getElementsByTag("a");

                            Elements eltitle2 = testlink1.getElementsByClass("_3wU53n");

                            Elements elpricebefore = link.getElementsByClass("_3auQ3N _2GcJzG");


                            Elements elpriceafter = link.getElementsByClass("_1vC4OE _2rQ-NK");

                            Elements elproductimage = link.getElementsByClass("_3BTv9X");


                            Elements discount = link.getElementsByClass("VGWI6T");


                            for (Element titleOfProduct : eltitle2) {
                                temp1 = titleOfProduct.text();

                            }

                            //product original price loop
                            for (Element priceOfProductBefore : elpricebefore) {
                                if(priceOfProductBefore == null){
                                    temp2 = "";
                                }else{
                                    temp2 = "Price before: " + priceOfProductBefore.text();
                                }
                            }

                            //product discounted price loop
                            for (Element priceOfProductAfter : elpriceafter) {
                                if(temp3 == null){
                                    temp3 = "Price: " + priceOfProductAfter.text();
                                }else{
                                    temp3 = "Discounted price: " + priceOfProductAfter.text();
                                }

                            }

                            //discount in number loop
                            for (Element productdiscount : discount) {
                                if(discount == null){
                                    temp4 = "";
                                }else{
                                    temp4 = "Discount: " + productdiscount.text();
                                }

                            }

                            ArrayList<String> linkArray = new ArrayList<String>();
                            for (Element elementLink : elLink) {
                                String MainLink = elementLink.attr("href");
                                linkArray.add(MainLink);
                            }
                            for (int i = 0; i < (linkArray.size()); i++) {
                                temp5 = "https://www.flipkart.com" + linkArray.get(0);
                            }


                            for(Element elimage : elproductimage){
                                temp6 = elimage.attr("src");
                            }

                            if (elpricebefore.text()==null)
                            {
                                permanent1 = "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
                            }

                            else
                            {
                                permanent1 = temp2 + "\n"  + temp3 + "\n" + temp4 + "\n";

                            }
                            temptitlestore.add(temp1);
                            mainlist.add(permanent1);
                            tempurlstore.add(temp5);
                            tempimageurl.add(temp6);
                        }
                    }
                }

                for (Element testlink2 : links1) {
                    Elements Testrun = testlink2.getElementsByClass("_1rcHFq");
                    String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                    String permanent1 = null;

                    if (Testrun.size() > 0) {

                        for (Element link1 : links1) {
                            Elements elLink1 = link1.getElementsByTag("a");

                            Elements eltitle2 = link1.getElementsByClass("_2cLu-l");


                            Elements elpricebefore1 = link1.getElementsByClass("_1vC4OE");


                            Elements elpriceafter1 = link1.getElementsByClass("_3auQ3N");

                            Elements elproductimage = link1.getElementsByClass("_3BTv9X");

                            Elements discount1 = link1.getElementsByClass("VGWI6T");


                            //product title loop
                            if (eltitle2.size() > 0) {
                                for (Element titleOfProduct : eltitle2) {
                                    temp1 = titleOfProduct.text();

                                }

                                //product original price loop
                                for (Element priceOfProductBefore : elpricebefore1) {
                                    temp2 = "Price before: " + priceOfProductBefore.text();
                                }

                                //product discounted price loop
                                for (Element priceOfProductAfter : elpriceafter1) {
                                    temp3 = "Discounted price: " + priceOfProductAfter.text();
                                }

                                //discount in number loop
                                for (Element productdiscount : discount1) {
                                    temp4 = "Discount: " + productdiscount.text();
                                }

                                ArrayList<String> linkArray = new ArrayList<String>();
                                for (Element elementLink : elLink1) {
                                    String MainLink = elementLink.attr("href");
                                    linkArray.add(MainLink);
                                }
                                for (int i = 0; i < 1; i++) {
                                    temp5 = "https://www.flipkart.com" + linkArray.get(0);
                                }

                                for(Element elimage : elproductimage){
                                    temp6 = elimage.attr("src");
                                }


                                if (elpricebefore1.text()==null)
                                {
                                    permanent1 = "Price :" + elpriceafter1.text() + "\n" + temp4 + "\n";
                                }

                                else
                                {
                                    permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                                }
                                temptitlestore.add(temp1);
                                mainlist.add(permanent1);
                                tempurlstore.add(temp5);
                                tempimageurl.add(temp6);
                            }
                        }
                    }
                }

                for (Element fashion : fashions) {
                    //BatchUpdates
                    Elements fashiontitle = fashion.getElementsByClass("_2mylT6");

                    String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                    String permanent1 = null;

                    if (fashiontitle.size() > 0) {
                        for (Element fash : fashions) {

                            Elements flink = fash.getElementsByTag("a");

                            Elements fashiontitlem = fashion.getElementsByClass("_2mylT6");

                            Elements fpricebefore = fash.getElementsByClass("_3auQ3N");


                            Elements fpriceafter = fash.getElementsByClass("_1vC4OE");

                            Elements elproductimage = fash.getElementsByClass("_3togXc");

                            Elements fdiscount = fash.getElementsByClass("VGWI6T");


                            for (Element ftitle : fashiontitlem) {
                                temp1 = ftitle.text();

                            }

                            //product original price loop
                            for (Element fproductpricebefore : fpricebefore) {
                                temp2 = "Price before: " + fproductpricebefore.text();
                            }

                            //product discounted price loop
                            for (Element fproductproceafter : fpriceafter) {
                                temp3 = "Discounted price: " + fproductproceafter.text();

                            }

                            //discount in number loop
                            for (Element fproductdiscount : fdiscount) {
                                temp4 = "Discount: " + fproductdiscount.text();

                            }

                            for(Element elimage : elproductimage){
                                temp6 = elimage.attr("src");
                            }


                            if (fpricebefore.text()==null)
                            {
                                permanent1 = "Price :" + fpriceafter.text() + "\n" + temp4 + "\n";
                            }

                            else
                            {
                                permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                            }
                            temptitlestore.add(temp1);
                            mainlist.add(permanent1);
                            tempurlstore.add(temp5);
                            tempimageurl.add(temp6);
                        }

                    }

                }

                for (Element maska : maskssans) {
                    Elements masktitle = maska.getElementsByClass("_2cLu-l");

                    String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                    String permanent1 = null;

                    if (masktitle.size() > 0) {
                        for (Element mask : maskssans) {

                            Elements mlink = mask.getElementsByTag("a");

                            Elements masktitle1 = maska.getElementsByClass("_2cLu-l");


                            Elements mpricebefore = mask.getElementsByClass("_3auQ3N");


                            Elements mpriceafter = mask.getElementsByClass("_1vC4OE");

                            Elements elproductimage =  mask.getElementsByClass("Zhf2z-");


                            Elements mdiscount = mask.getElementsByClass("VGWI6T");

                            for (Element mtitle : masktitle1) {
                                temp1 = mtitle.text();

                            }

                            //product original price loop
                            for (Element mproductpricebefore : mpricebefore) {
                                temp2 = "Price before: " + mproductpricebefore.text();
                            }

                            //product discounted price loop
                            for (Element mproductproceafter : mpriceafter) {
                                temp3 = "Discounted price: " + mproductproceafter.text();

                            }

                            //discount in number loop
                            for (Element mproductdiscount : mdiscount) {
                                temp4 = "Discount: " + mproductdiscount.text();

                            }

                            for(Element elimage : elproductimage){
                                temp6 = elimage.attr("src");
                            }

                            ArrayList<String> linkArray = new ArrayList<String>();
                            for (Element melementLink : mlink) {
                                String fMainLink = melementLink.attr("href");
                                linkArray.add(fMainLink);
                            }
                            for (int i = 0; i < (linkArray.size()); i++) {
                                temp5 = "https://www.flipkart.com" + linkArray.get(0);
                            }


                            if (mpricebefore.text()==null)
                            {
                                permanent1 = "Price :" + mpriceafter.text() + "\n" + temp4 + "\n";
                            }

                            else
                            {
                                permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                            }
                            temptitlestore.add(temp1);
                            mainlist.add(permanent1);
                            tempurlstore.add(temp5);
                            tempimageurl.add(temp6);

                        }
                    }

                }
                return mainlist;
            } catch (Exception e) {
                ArrayList<String> exception = new ArrayList<String>();
                String ex = e.toString();
                exception.add(ex);
                return exception;
            }
        }
    }

    private class Snapdeal extends AsyncTask<String, Void, ArrayList<String>> {
        ArrayList<String> temptitlestore = new ArrayList<>();
        ArrayList<String> tempurlstore = new ArrayList<>();
        ArrayList<String> tempimageurl = new ArrayList<>();
        String link;

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            String product, urlstore,title,imagelink;

            super.onPostExecute(s);
            for (int j = 0; j < 6; j++) {
                product = s.get(j);
                title = temptitlestore.get(j);
                titleallproducts.add(title);
                urlstore = tempurlstore.get(j);
                allproducts.add(product);
                producturl.add(urlstore);
                imagelink = tempimageurl.get(j);
                imageurls.add(imagelink);
            }
            String seemore = "SHOW MORE PRODUCTS ON SNAPDEAL......";
            String seemoreimage = "https://previews.123rf.com/images/brisker/brisker1605/brisker160500027/58489983-16-icons-of-different-products-categories-for-online-shops.jpg";
            allproducts.add("");
            titleallproducts.add(seemore);
            producturl.add(link);
            imageurls.add(seemoreimage);

        }


        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements links = doc.getElementsByClass("col-xs-6  favDp product-tuple-listing js-tuple ");
                ArrayList<String> mainlist = new ArrayList<String>();
                link = strings[0];
                String title = "SNAPDEAL PRODUCTS";
                String url = "https://www.snapdeal.com";
                String imageurl = "https://www.apkmirror.com/wp-content/uploads/2018/12/5c0671b80df76.png";
                temptitlestore.add(title);
                tempurlstore.add(url);
                tempimageurl.add(imageurl);
                mainlist.add("");


                for (Element link : links) {
                    String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null, temp6 = null;
                    String permanent1 = null;

                    Elements elLink = link.getElementsByTag("a");

                    Elements eltitle = link.getElementsByClass("product-title"); //for product title

                    Elements elpricebefore = link.getElementsByClass("lfloat product-desc-price strike ");

                    Elements elpriceafter = link.getElementsByClass("lfloat product-price");

                    Elements elproductimage = link.getElementsByClass("product-image ");

                    Elements discount = link.getElementsByClass("product-discount");


                    //product title loop
                    for (Element titleOfProduct : eltitle) {
                        temp1 = titleOfProduct.text();
                    }

                    //product original price loop
                    for (Element priceOfProductBefore : elpricebefore) {
                        temp2 = "Price before: " + priceOfProductBefore.text();
                    }

                    //product discounted price loop
                    for (Element priceOfProductAfter : elpriceafter) {
                        temp3 = "Discounted price: " + priceOfProductAfter.text();
                    }

                    //discount in number loop
                    for (Element productdiscount : discount) {
                        temp4 = "Discount: " + productdiscount.text();
                    }

                    for(Element elimage : elproductimage){
                        temp6 = elimage.attr("src");
                    }

                    ArrayList<String> linkArray = new ArrayList<String>();
                    for (Element elementLink : elLink) {
                        String MainLink = elementLink.attr("href");
                        linkArray.add(MainLink);
                    }

                    for (int j = 0; j < 1; j++) {
                        temp5 = linkArray.get(0);
                    }

                    if (elpricebefore.text()==null)
                    {
                        permanent1 = "\n"+ "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
                    }

                    else
                    {
                        permanent1 ="\n" + temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                    }
                    temptitlestore.add(temp1);
                    mainlist.add(permanent1);
                    tempurlstore.add(temp5);
                    tempimageurl.add(temp6);

                }
                return mainlist;
            } catch (Exception e) {
                ArrayList<String> exception = new ArrayList<String>();
                String ex = e.toString();
                exception.add(ex);
                return exception;
            }
        }
    }

    public class Paytm extends AsyncTask<String, Void, ArrayList<String>> {
        ArrayList<String> tempurlstore = new ArrayList<>();
        ArrayList<String> temptitlestore = new ArrayList<>();
        ArrayList<String> tempimageurl = new ArrayList<>();
        String link;

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            String product, urlstore,title,imagelink;
            super.onPostExecute(s);
            for (int j = 0; j < 6; j++) {
                product = s.get(j);
                title = temptitlestore.get(j);
                titleallproducts.add(title);
                urlstore = tempurlstore.get(j);
                allproducts.add(product);
                producturl.add(urlstore);
                imagelink = tempimageurl.get(j);
                imageurls.add(imagelink);
            }
            String seemore = "SHOW MORE PRODUCTS ON PAYTM MALL......";
            String seemoreimage = "https://previews.123rf.com/images/brisker/brisker1605/brisker160500027/58489983-16-icons-of-different-products-categories-for-online-shops.jpg";
            allproducts.add("");
            titleallproducts.add(seemore);
            producturl.add(link);
            imageurls.add(seemoreimage);
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements links = doc.getElementsByClass("_3WhJ");
                ArrayList<String> mainlist = new ArrayList<String>();
                link = strings[0];
                String title = "PAYTM MALL PRODUCTS";
                String url = "https://www.paytmmall.com";
                String imageurl = "https://img.etimg.com/thumb/width-640,height-480,imgsize-19837,resizemode-1,msid-60726247/.jpg";
                temptitlestore.add(title);
                tempurlstore.add(url);
                tempimageurl.add(imageurl);
                mainlist.add("");

                for (Element link : links) {
                    String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                    String permanent1 = null;

                    Elements elLink = link.getElementsByTag("a");

                    Elements eltitle = link.getElementsByClass("UGUy"); //for product title

                    Elements elpricebefore = link.getElementsByClass("dQm2");

                    Elements elpriceafter = link.getElementsByClass("_1kMS");

                    Elements elproductimage = link.getElementsByTag("img");

                    Elements discount = link.getElementsByClass("c-ax");


                    //product title loop
                    for (Element titleOfProduct : eltitle) {
                        temp1 = titleOfProduct.text();
                    }

                    //product original price loop
                    for (Element priceOfProductBefore : elpricebefore) {
                        String s1 = priceOfProductBefore.text();
                        char[] a1 = s1.toCharArray();
                        String new1 = "₹ ";
                        int diff = (a1.length)-3;

                        for (int x = 0; x<diff-1; x++){
                            new1 = new1 + a1[x];
                        }
                        temp2 = "Price before: " + new1;
                    }

                    //product discounted price loop
                    for (Element priceOfProductAfter : elpriceafter) {
                        temp3 = "Discounted price: " + priceOfProductAfter.text();
                    }

                    //discount in number loop
                    for (Element productdiscount : discount) {
                        temp4 = "Discount: " + productdiscount.text();
                    }

                    ArrayList<String> linkArray = new ArrayList<String>();
                    for (Element elementLink : elLink) {
                        String MainLink = elementLink.attr("href");
                        linkArray.add(MainLink);
                    }
                    for (int i = 0; i < linkArray.size(); i++) {
                        temp5 = "https://www.paytmmall.com" + linkArray.get(0);
                    }

                    for(Element elimage : elproductimage){
                        temp6 = elimage.attr("src");
                    }

                    if (elpricebefore.text()==null)
                    {
                        permanent1 = "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
                    }

                    else
                    {
                        permanent1 = temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                    }
                    temptitlestore.add(temp1);
                    mainlist.add(permanent1);
                    tempurlstore.add(temp5);
                    tempimageurl.add(temp6);

                }
                return mainlist;
            } catch (Exception e) {
                ArrayList<String> exception = new ArrayList<String>();
                String ex = e.toString();
                exception.add(ex);
                return exception;
            }
        }
    }

    private class Shopclues extends AsyncTask<String, Void, ArrayList<String>> {
        ArrayList<String> temptitlestore = new ArrayList<>();
        ArrayList<String> tempurlstore = new ArrayList<>();
        ArrayList<String> tempimageurl = new ArrayList<>();
        String link;

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            String product, urlstore,title,imagelink;
            super.onPostExecute(s);
            for (int j = 0; j < 6; j++) {
                product = s.get(j);
                title = temptitlestore.get(j);
                titleallproducts.add(title);
                urlstore = tempurlstore.get(j);
                allproducts.add(product);
                producturl.add(urlstore);
                imagelink = tempimageurl.get(j);
                imageurls.add(imagelink);
            }
            String seemore = "SHOW MORE PRODUCTS ON SHOPCLUES......";
            String seemoreimage = "https://previews.123rf.com/images/brisker/brisker1605/brisker160500027/58489983-16-icons-of-different-products-categories-for-online-shops.jpg";
            allproducts.add("");
            titleallproducts.add(seemore);
            producturl.add(link);
            imageurls.add(seemoreimage);
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements links = doc.getElementsByClass("column col3 search_blocks");
                ArrayList<String> mainlist = new ArrayList<String>();
                link = strings[0];
                String title = "SHOPCLUES PRODUCTS";
                String url = "https://www.shopclues.com";
                String imageurl = "https://2.bp.blogspot.com/-PhEOWIZ5RUc/WszElAqJseI/AAAAAAAAAj8/QSn5SborV2wuzVsYTCm7SJk7aoH08OQ2ACPcBGAYYCw/w680/shopclue.png";
                temptitlestore.add(title);
                tempurlstore.add(url);
                tempimageurl.add(imageurl);
                mainlist.add("");


                for (Element link : links) {
                    String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                    String permanent1 = null;

                    Elements elLink = link.getElementsByTag("a");

                    Elements eltitle = link.getElementsByTag("h2"); //for product title

                    Elements elpricebefore = link.getElementsByClass("old_prices");

                    Elements elpriceafter = link.getElementsByClass("p_price");

                    Elements elproductimage = link.getElementsByTag("img");

                    Elements discount = link.getElementsByClass("prd_discount");


                    //product title loop
                    for (Element titleOfProduct : eltitle) {
                        temp1 = titleOfProduct.text();
                    }

                    //product original price loop
                    for (Element priceOfProductBefore : elpricebefore) {
                        temp2 = "Price before: " + priceOfProductBefore.text();
                    }

                    //product discounted price loop
                    for (Element priceOfProductAfter : elpriceafter) {
                        temp3 = "Discounted price: " + priceOfProductAfter.text();
                    }

                    //discount in number loop
                    for (Element productdiscount : discount) {
                        temp4 = "Discount: " + productdiscount.text();
                    }

                    for(Element elimage : elproductimage){
                        temp6 = elimage.attr("src");
                    }

                    ArrayList<String> linkArray = new ArrayList<String>();
                    for (Element elementLink : elLink) {
                        String MainLink = elementLink.attr("href");
                        linkArray.add(MainLink);
                    }

                    for (int j = 0; j < 1; j++) {
                        temp5 = "https:" + linkArray.get(0);
                    }

                    if (elpricebefore.text()==null)
                    {
                        permanent1 = "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
                    }

                    else
                    {
                        permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                    }
                    temptitlestore.add(temp1);
                    mainlist.add(permanent1);
                    tempurlstore.add(temp5);
                    tempimageurl.add(temp6);

                }
                return mainlist;
            } catch (Exception e) {
                ArrayList<String> exception = new ArrayList<String>();
                String ex = e.toString();
                exception.add(ex);
                return exception;
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }

}

