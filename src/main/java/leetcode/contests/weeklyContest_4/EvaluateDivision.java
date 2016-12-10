package leetcode.contests.weeklyContest_4;

import java.util.ArrayList;

/**
 * Created by FengSi on 2016/09/11 at 15:47.
 * unsolved
 */
public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
        ArrayList<String> map = new ArrayList<>(); //mapping "variable name" -> "index"
        double result[] = new double[query.length]; //result array
        double[][] relation; //the 2D array that contains ratios of all pairs
        /*I use a 2D array to represent the relation(ratio) between all input pairs
        ex.   if a/b = 2.0, b/c = 3/0, then the 2D array(called "relation:) is:
                a    b   c
             a 1.0  2.0 6.0
             b 0.5  1.0 3.0
             c 1/6  1/3 1.0
         our task is to fill out this array.
        */
        //but an array is indexed by numbers, not strings. So we need a mapping: String->Integer, or "variable name" -> "its index in the 2D array" for the above example, a->0, b->1, c->2. This feature is implemented by the following for loop
        int num=0;
        for (String[] equation : equations) {
            if (!map.contains(equation[0])) {
                map.add(equation[0]);
                num++;
            }
            if (!map.contains(equation[1])) {
                map.add(equation[1]);
                num++;
            }
        }
        relation = new double[num][num]; //create the 2D array with the number of variables
        for(int i=0;i<equations.length;i++){ //fill out the ratio provided by the input
            int ind1 = map.indexOf(equations[i][0]);
            int ind2 = map.indexOf(equations[i][1]);
            relation[ind1][ind2] = values[i];
        }
        for(int i=0;i<relation.length;i++){ //fill out 1.0's and the reciprocals, this loop can be combine with the above loop
            for(int j=0;j<relation.length;j++){
                if(i==j) relation[i][j] = 1;
                if(relation[i][j]!=0) relation[j][i] = 1/relation[i][j];
            }
        }
        /*until this point, the 2D array is:
                a    b   c
             a 1.0  2.0 0.0
             b 0.5  1.0 3.0
             c 0.0  1/3 1.0
         We need <a,c> and <c,a> which are the ratio of a,c. The loops below fill out all missing elements
        */
        boolean notQuit = true;
        while(notQuit){
            notQuit = false;
            for(int i=0;i<relation.length;i++){
                breakHere: //break point
                for(int j=0;j<relation.length;j++){
                    if(relation[i][j]==0){ //0 element detect, trying to determine this ratio
                        for(int k=0;k<relation.length;k++){ //scan this column, from top to bottom
                            if(k!=i){
                                if(relation[k][j]!=0) { //find a variable that is already associated with the variable of this column
                                    if(relation[i][k]!=0){ //find a variable that is associated with the variable found in the line above.
                                        relation[i][j] = relation[i][k] * relation[k][j]; //calculate ratio and reciprocal
                                        relation[j][i] = 1/relation[i][j];
                                        notQuit=true; //if a zero element is changed into non-zero, always restart the outer for loop
                                        break breakHere;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
       /* 2D array construction complete, current 2D array:
                a    b   c
             a 1.0  2.0 6.0
             b 0.5  1.0 3.0
             c 1/6  1/3 1.0
            this 2D array is aready for any query. Note that if 2 variables are not associated, their element in the 2D is array remains 0.
       */
        for(int i=0;i<query.length;i++){ //this loop fills out the result array
            int ind1 = map.indexOf(query[i][0]);
            int ind2 = map.indexOf(query[i][1]);
            if(ind1<0 || ind2<0) result[i] = -1.0; //unknown variable
            else if(relation[ind1][ind2]!=0)
                result[i] = relation[ind1][ind2];
            else
                result[i] = -1.0; //not associated variables
        }
        return result;
    }
}

/*
399. Evaluate Division
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> euqations, vector<double>& values, vector<pair<string, string>> query . where equations.size() == values.size(),the values are positive. this represents the equations.return vector<double>. .
The example above: equations = [ ["a", "b"], ["b", "c"] ]. values = [2.0, 3.0]. queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */