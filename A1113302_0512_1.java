import java.util.*;

class Customer extends Thread {
    dumplingShop a;
    int no;

    Customer(dumplingShop a, int no){
        this.a = a;
        this.no = no;
    }

    public void run() {      
        while(a.soldOut=false){
            int type = (int)(Math.ceil(Math.random()*3));
            int sale = (int)(Math.ceil(Math.random()*50));
            a.saleDumpling(no, type, sale);
            try {
                sleep(3000);
            } catch(InterruptedException e) {       
            }    
        }
    } 
}

class dumplingShop {
    int numCustomer;
    int pork = 5000;
    int beef = 3000;
    int vegetable = 1000;
    public boolean soldOut = false; 
    Customer[] customer;

    dumplingShop(int num){
        numCustomer = num;
    }

    public synchronized void saleDumpling(int a,int b,int c) {
        int no = a;
        int type = b;
        int sale = c;

        if(type == 1) {
            if(pork==0){
                System.out.println("豬肉賣完了!!");
            } else {
                if(pork <= sale) {
                    sale = pork;
                    pork -= pork;
                    System.out.println("第" + (no + 1) + "位顧客點" + sale + "顆豬肉" + "\t" + "豬肉剩" + pork + "顆" + "\n");
                } else if(pork > sale){
                    pork -= sale;
                    System.out.println("第" + (no + 1) + "位顧客點" + sale + "顆豬肉" + "\t" + "豬肉剩" + pork + "顆" + "\n");
                }
            }   
        }else if(type == 2){
            if(beef==0){
                System.out.println("牛肉賣完了!!");
            }else{
                if(beef <= sale) {
                    sale = beef;
                    beef -= beef;
                    System.out.println("第" + (no + 1) + "位顧客點" + sale + "顆牛肉" + "\t" + "牛肉剩" + beef + "顆" + "\n");
                } else if(beef>sale){    
                    beef -= sale;
                    System.out.println("第" + (no + 1) + "位顧客點" + sale + "顆牛肉" + "\t" + "牛肉剩" + beef + "顆" + "\n");
                }
            }
        }else if(type == 3){
            if(vegetable==0){
                System.out.println("蔬菜水餃賣完了!!");
            } else{   
                if(vegetable <= sale) {
                    sale = vegetable;
                    vegetable -= vegetable;
                    System.out.println("第" + (no + 1) + "位顧客點" + sale + "顆蔬菜" + "\t" + "蔬菜剩" + vegetable + "顆" + "\n");
                } else if(vegetable>sale){  
                    vegetable -= sale;
                    System.out.println("第" + (no + 1) + "位顧客點" + sale + "顆蔬菜" + "\t" + "蔬菜剩" + vegetable + "顆" + "\n");
                }
            }
        }    
        
        if(pork==0 && beef==0 && vegetable==0){
            soldOut = true;
        }
    }

    public void startSale() {
        Customer[] customer =new Customer[numCustomer]; 
        for(int i = 0 ; i < numCustomer ; i++) {
            customer[i] = new Customer(this,i);
        }
        for(int i = 0 ; i < numCustomer ; i++) {
            customer[i].start();
        }   
    }
}

public class A1113302_0512_1 {
    public static void main(String[] argv) {
        System.out.println("請輸入同時光顧的顧客數:");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        dumplingShop a = new dumplingShop(num);
        a.startSale();

    }
}
