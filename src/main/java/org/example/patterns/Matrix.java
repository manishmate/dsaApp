package org.example.patterns;

import java.util.ArrayList;

public class Matrix {
    //spiralTraversal : O(mn) time, O(1) space
    //rotateMatrix : O(n²) time, O(1) space
    //setZeroes : O(mn) time, O(1) space
    //searchMatrix : O(log(m*n)) time, O(1) space
    //searchMatrix2 : O(m+n) time, O(1) space

    /**
     * Spiral matrix
     * int[][] mat = {
     *     {1,2,3},
     *     {4,5,6},
     *     {7,8,9}
     * }
     *
     * Output:
     * [1,2,3,6,9,8,7,4,5]
     */
    public ArrayList<Integer> spirallyTraverse(int mat[][]) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int top = 0;
        int left = 0;
        int right = mat[0].length - 1;
        int bottom = mat.length - 1;
        int direction = 0;
        while(top<=bottom && left<=right){
            if(direction==0){
                for(int i=left; i<=right; i++){
                    res.add(mat[top][i]);
                }
                top++;
            }
            if(direction==1){
                for(int i=top; i<=bottom; i++){
                    res.add(mat[i][right]);
                }
                right--;
            }
            if(direction==2){
                for(int i=right; i>=left; i--){
                    res.add(mat[bottom][i]);
                }
                bottom--;
            }
            if(direction==3){
                for(int i=bottom; i>=top; i--){
                    res.add(mat[i][left]);
                }
                left++;
            }
            direction = (direction+1)%4;
        }
        return res;
    }

    /**
     * ROTATE MATRIX
     */


    /**  ROTATE MATRIX BY 90% Clockwise
     *  APPROACH :
     *   1) REVERSE COLUMNS + TRANSPOSE
     *   2) TRANSPOSE + REVERSE ROWS
     *
     *  =======ANTI-Clockwise=========//
     *  APPROACH :
     *  1) TRANSPOSE + REVERSE COLUMNS
     *  2) REVERSE ROWS + TRANSPOSE
     */

    //TWO APPROACH
    //APROACH 1

    /**
     * Input:
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * Out :
     * 7 4 1
     * 8 5 2
     * 9 6 3
     * @param mat
     */
    static void rotate(int mat[][]) {
        //steps
        //this works for sqaure matrix n*n not for rectangular
        //1)reverse each COLUMN of transverse matrix
        //2)transpose matrix -> means a[i][i] to a[j][i] -> will bcome 90

        int n = mat.length;
        for(int i=0;i<n/2;i++){
            int temp[]= mat[i];
            mat[i]= mat[n-1-i];
            mat[n-i-1]=temp;
        }

        //transpose
        // j=i+1 to swap only above diagonal , this will avoid duplicate swap
        // each pair will be swapped only once and skip diagonal i==j

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int temp  = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        //APPROACH 2
        //We can also achive this by 1) transpose first and then 2) reverse ROWS
        //But ROws reverse need double for loop, as inner content needs to be reverse.

    }

    /**
     * Set Zero in matrix
     * input :
     * 1 2 3
     * 4 0 6
     * 7 8 9
     * out:
     * 1 0 3
     * 0 0 0
     * 7 0 9
     */

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check first row
        for(int j=0; j<cols; j++){
            if(matrix[0][j] == 0)
                firstRowZero = true;
        }

        // Check first column
        for(int i=0; i<rows; i++){
            if(matrix[i][0] == 0)
                firstColZero = true;
        }

        // Mark rows and columns
        //Why i,j start from 1 instead of 0?
        //Because:  First row (row 0) is being used as column markers
        //First column (col 0) is being used as row markers
        //We must not overwrite those while scanning.
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero using markers
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){

                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        // Zero first row
        if(firstRowZero){
            for(int j=0; j<cols; j++)
                matrix[0][j]=0;
        }

        // Zero first column
        if(firstColZero){
            for(int i=0; i<rows; i++)
                matrix[i][0]=0;
        }
    }

    /**
     * Search in 2D Matrix
     */
    public boolean searchMatrix(int[][] mat, int x) {
        int m = mat.length;
        int n = mat[0].length;
        int left = 0;
        int right =  m*n-1;

        while(left<=right){
            int mid = left+(right-left)/2;
            int row = mid/n;
            int col = mid%n;
            int midValue = mat[row][col];

            if(midValue==x)
                return true;
            else if(midValue>x)
                right = mid-1;
            else
                left = mid+1;
        }
        return false;
    }

    //Approach 2
    public boolean searchMatrix2(int[][] mat, int x) {
        int row = 0;
        int col = mat[0].length - 1;

        while(row < mat.length && col >= 0){
            if(mat[row][col] == x)
                return true;

            else if(mat[row][col] > x)
                col--;       // move left
            else
                row++;       // move down
        }
        return false;
    }
}
