package software.davidson.ian.slidingWindow;

public class MaximizeConfusionOfExam {

    public static void main(String [] args){
        MaximizeConfusionOfExam maximizeConfusionOfExam = new MaximizeConfusionOfExam();
        System.out.println(maximizeConfusionOfExam.maxConsecutiveAnswers("TTFF", 2));
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {

        char [] arr = answerKey.toCharArray();
        int ans = 0;
        int st =0, c =0;
        for(int i =0;i <arr.length;i ++){

            if(arr[i]=='F'){
                c++;
            }
            while(c>k){

                if(arr[st]=='F')
                    c--;
                st++;

            }
            ans =Math.max(ans,i-st+1);


        }
        st =0;
        c =0;
        for(int i =0;i <arr.length;i ++){

            if(arr[i]=='T'){
                c++;
            }
            while(c>k){

                if(arr[st]=='T')
                    c--;
                st++;

            }
            ans =Math.max(ans,i-st+1);


        }
        return ans;
    }
}
