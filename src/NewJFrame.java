
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class NewJFrame extends javax.swing.JFrame {
   private static int[][] sub_key = new int[16][48];
        private static int[] Ss = new int[28];
	private static int[] Bb = new int[28];
  static DataInputStream dataInputStream ;
    static DataOutputStream dataOutputStream ;
    static ServerSocket serversocket ;
    static Socket socket ;
    public static String KeyInput ="ABCDEFGT";
   
        public  static boolean H_D  ; 


    public NewJFrame() {
        initComponents();
    }
   
      public static String KeyHil ="ddcf"; 
      
     


  
      private static int[][] getKey_Ma() { 
      
    
      
  
      int q = KeyHil.length()% 2 ;
     
       if ( q != 0) {  
           JOptionPane.showMessageDialog(null,"Cannot Form a square matrix");
        } 
       
        int length = KeyHil.length() ;
       int[][] keyMatrix = new int[length][length];
       
        int g = 0;  
        for (int i = 0; i < 2; i++)  
        {  
            for (int j = 0; j < 2; j++)  
            {  
              keyMatrix[i][j] = ((int) KeyHil.charAt(g))-97 ; 
         
               
                g++; 
              
            }  
           
        }
       
        return keyMatrix ; 
     
      }
        private static void V_Matrix(int[][] key_ATch_Matrix) {  
        int d = key_ATch_Matrix[0][0] * key_ATch_Matrix[1][1] - key_ATch_Matrix[0][1] * key_ATch_Matrix[1][0];  
        if(d == 0) {  
             JOptionPane.showMessageDialog(null,"Det equals to zero, invalid key matrix!");
          
        }  
    }
   
        private static void isValidReverseMatrix(int[][] keyMatrix, int[][] Inverse_Matrix) {  
        int[][] pro = new int[2][2];  
        pro[0][0] = (keyMatrix[0][0]*Inverse_Matrix[0][0] + keyMatrix[0][1] * Inverse_Matrix[1][0]) % 26;  
        pro[0][1] = (keyMatrix[0][0]*Inverse_Matrix[0][1] + keyMatrix[0][1] * Inverse_Matrix[1][1]) % 26;  
        pro[1][0] = (keyMatrix[1][0]*Inverse_Matrix[0][0] + keyMatrix[1][1] * Inverse_Matrix[1][0]) % 26;  
        pro[1][1] = (keyMatrix[1][0]*Inverse_Matrix[0][1] + keyMatrix[1][1] * Inverse_Matrix[1][1]) % 26;  
   
        if(pro[0][0] != 1 || pro[0][1] != 0 || pro[1][0] != 0 || pro[1][1] != 1) {  
            throw new java.lang.Error("Invalid reverse matrix found!");  
        }  
    } 
        
        
        
        
        
         
   
    private static int[][] reverseMatrix(int[][] keyMatrix) {  
        int detmod26 = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % 26; 
        int factor;  
        int[][] reverseMatrix = new int[2][2];  
     
        for(factor=1; factor < 26; factor++)  
        {  
            if((detmod26 * factor) % 26 == 1)  
            {  
                break;  
            }  
        }  
        reverseMatrix[0][0] = keyMatrix[1][1]           * factor % 26;  
        reverseMatrix[0][1] = (26 - keyMatrix[0][1])    * factor % 26;  
        reverseMatrix[1][0] = (26 - keyMatrix[1][0])    * factor % 26;  
        reverseMatrix[1][1] = keyMatrix[0][0]           * factor % 26;  
        return reverseMatrix;  
    }  
        
         private static int[] xorHill(int[] h, int[] b) {
                 
           int inputBits[] = new int[8*h.length]; 
           for(int i=0 ; i < h.length  ; i++) {
		
			String str_1 = Integer.toBinaryString(h[i]);
			String str_2 = Integer.toBinaryString(b[i]);
		
			while(str_1.length() < 8) {
				str_1 = "0" + str_1;
			}
                        while(str_2.length() < 8) {
				str_2 = "0" + str_2;
			}
			for(int j=0 ; j < 8 ; j++) {
				inputBits[(8*i)+j] = Integer.parseInt(str_1.charAt(j) + "");
                                inputBits[(8*i)+j] = Integer.parseInt(str_2.charAt(j) + "");
			}
		} 
		int an[] = new int[h.length];
		for(int i=0 ; i < h.length ; i++) {
			an[i] = h[i]^b[i];
		}
		return an;
	}
        
         
         
             public static String decrypt(String phrase)  
    {  
        int i;  
        int[][] keyMatrix, revKeyMatrix;  
        ArrayList<Integer> phraseToNum = new ArrayList<>();  
        ArrayList<Integer> phraseDecoded = new ArrayList<>();  
        phrase = phrase.replaceAll("[^a-zA-Z]","").toUpperCase();  
  
     
        keyMatrix = getKey_Ma();  
    
        V_Matrix(keyMatrix);  
        
   
     
        
          for(i=0; i < phrase.length(); i++) {  
            phraseToNum.add(phrase.charAt(i) - (65 ));  
           
        }  
        
      
  
        revKeyMatrix = reverseMatrix(keyMatrix);  
        
       
        isValidReverseMatrix(keyMatrix, revKeyMatrix);  
        
        for(i=0; i < phraseToNum.size(); i += 2) {  
            phraseDecoded.add((revKeyMatrix[0][0] * phraseToNum.get(i) + revKeyMatrix[0][1] * phraseToNum.get(i+1)) % 26);  
            phraseDecoded.add((revKeyMatrix[1][0] * phraseToNum.get(i) + revKeyMatrix[1][1] * phraseToNum.get(i+1)) % 26);  
            
        }  
     char c[];
         String arr = "";
      for( int g =0; g < phraseDecoded.size(); g += 1) {  
         c=  Character.toChars(phraseDecoded.get(g) + (65 ));  
        arr += String.valueOf(c);
       
          // Print the result 
      }
       String arr1 = "";
         int n=arr.length()-1;
          if(arr.charAt(n)=='Q') {  
           
             for(i=0; i < arr.length()-1; i++) {  
          
             arr1 += String.valueOf(arr.charAt(i));
           
        }     
        }  else{
           for(i=0; i < arr.length(); i++) {  
               arr1 += String.valueOf(arr.charAt(i));
           }
        }  
     
      return arr1;
    }
                  
       private static String EncrytionOrDecryption(boolean isDecrypt,String PlainTextInput) {
         
         
          
           int inputBits[] = new int[ 8 *PlainTextInput.length()]; 
           for(int i=0 ; i < PlainTextInput.length() ; i++) {
			
			String s = Integer.toBinaryString( (int)PlainTextInput.charAt(i) );
			
			
			while(s.length() < 8) {
				s = "0" + s;
			}
                 
			// Add the 4 bits we have extracted into the array of bits.
			for(int j=0 ; j < 8 ; j++) {
				inputBits[(8*i)+j] = Integer.parseInt(s.charAt(j) + "");
                                
			}
                          
		} 
        
           int keyBits[] = new int[64];
           for(int i=0 ; i < KeyInput.length() ; i++) {
			String s = Integer.toBinaryString( (int)KeyInput.charAt(i) );
			while(s.length() < 8) {
				s = "0" + s;
			}
                        
			for(int j=0 ; j < 8 ; j++) {
				keyBits[(8*i)+j] = Integer.parseInt(s.charAt(j) + "");
			}
                   
		}
              
          
            if(isDecrypt){
       return permute(inputBits, keyBits, true);
            }
            else{
                  
            return permute(inputBits, keyBits, false);
            
            }}
            
            
  private static String permute(int[] inputBits, int[] keyBits , boolean isDecrypt) {
          
             
	
		int newBits[] = new int[inputBits.length];
		for(int i=0 ; i < inputBits.length ; i++) {
			newBits[i] = inputBits[Initial_Permutation[i]-1];
		}
		
	
		int L[] = new int[32];
		int R[] = new int[32];
             
		System.arraycopy(newBits, 0, L, 0, 32);
		System.arraycopy(newBits, 32, R, 0, 32);
		
		
	
		
		
                int i;
		for(i=0 ; i < 28 ; i++) {
			Ss[i] = keyBits[Permuted_Choice_1[i]-1];
		}
		for( ; i < 56 ; i++) {
			Bb[i-28] = keyBits[Permuted_Choice_1[i]-1];
		}
		
                
		for(int n=0 ; n < 16 ; n++) {
			
			
			int newR[] = new int[0];
                        
                         



		        if(isDecrypt) {
               
				newR = fiestel(R, sub_key[15-n]);
                                   
                        }
                        else{
			newR = fiestel(R, KR(n, keyBits));
                                }         
			
			int newL[] = xor(L, newR);
			L = R;
			R = newL;
		
		}
                
           
		int output[] = new int[64];
		System.arraycopy(R, 0, output, 0, 32);
		System.arraycopy(L, 0, output, 32, 32);
		int finalOutput[] = new int[64];
		
		for(i=0 ; i < 64 ; i++) {
			finalOutput[i] = output[Final_permutation[i]-1];
		}
		
	
		String hex = new String();
		for(i=0 ; i < 8 ; i++) {
			String bin = new String();
			for(int j=0 ; j < 8 ; j++) {
				bin += finalOutput[(8*i)+j];
			}
			int decimal = Integer.parseInt(bin, 2);
                         
			hex += (char)decimal;
		}
                 
			///System.out.print("Encrypted text: ");
		
		// String finalOutputHex = hex.toUpperCase();
                    
		return hex;
        }
         private static int[] xor(int[] a, int[] b) {
		// Simple xor function on two int arrays
		int answer[] = new int[a.length];
		for(int i=0 ; i < a.length ; i++) {
			answer[i] = a[i]^b[i];
		}
		return answer;
	}
                
        private static int[] KR(int round, int[] key) {
	
		int lCg[] = new int[28];
		int lDg[] = new int[28];
		
		int rotationTimes = (int) rotations[round];
	
		lCg = leftS(Ss, rotationTimes);
		lDg = leftS(Bb, rotationTimes);
		int CnDn[] = new int[56];
		System.arraycopy(lCg, 0, CnDn, 0, 28);
		System.arraycopy(lDg, 0, CnDn, 28, 28);
	
		int Kn[] = new int[48];
		for(int i=0 ; i < Kn.length ; i++) {
			Kn[i] = CnDn[Permuted_Choice_2[i]-1];
		}
		
	
		sub_key[round] = Kn;
                  
		Ss = lCg;
		Bb = lDg;
		return Kn;
	}
        private static int[] fiestel(int[] R, int[] roundKey) {
		
		int expandedR[] = new int[48];
		for(int i=0 ; i < 48 ; i++) {
			expandedR[i] = R[Expansion[i]-1];
		}
		
		int temp[] = xor(expandedR, roundKey);
		
		int output[] = sBlock(temp);
		return output;
	}
        	
        private static int[] sBlock(int[] bits) {
		int bitsout[] = new int[32];
		
		for(int i=0 ; i < 8 ; i++) {
			
			int row_[] = new int [2];
			row_[0] = bits[6*i];
			row_[1] = bits[(6*i)+5];
			String _Row = row_[0] + "" + row_[1];
			
			int _column[] = new int[4];
			_column[0] = bits[(6*i)+1];
			_column[1] = bits[(6*i)+2];
			_column[2] = bits[(6*i)+3];
			_column[3] = bits[(6*i)+4];
			String __Column = _column[0] +""+ _column[1] +""+ _column[2] +""+ _column[3];
		
			int i_Row = Integer.parseInt(_Row, 2);
			int i_Column = Integer.parseInt(__Column, 2);
			int x = S_boxes[i][(i_Row*16) + i_Column];
			
			String s = Integer.toBinaryString(x);
			
			while(s.length() < 4) {
				s = "0" + s;
			}
			for(int j=0 ; j < 4 ; j++) {
				bitsout[(i*4) + j] = Integer.parseInt(s.charAt(j) + "");
			}
		}
		
		int finbit[] = new int[32];
		for(int i=0 ; i < 32 ; i++) {
			finbit[i] = bitsout[Permutation[i]-1];
		}
		return finbit;
	}
        
            
            
private static int[] leftS(int[] bits, int n) {
		
		int an[] = new int[bits.length];
		System.arraycopy(bits, 0, an, 0, bits.length);
		for(int i=0 ; i < n ; i++) {
			int temp = an[0];
			for(int j=0 ; j < bits.length-1 ; j++) {
				an[j] = an[j+1];
			}
			an[bits.length-1] = temp;
		}
		return an;
	}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        TextServer = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaServer = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SERVER", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 51, 51))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter here", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        TextServer.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Send By Hill");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Send By DES");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TextServer)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                        .addGap(39, 39, 39))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(TextServer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );

        TextAreaServer.setBackground(new java.awt.Color(204, 204, 204));
        TextAreaServer.setColumns(20);
        TextAreaServer.setRows(5);
        jScrollPane1.setViewportView(TextAreaServer);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       
 try{
    
        String Note = ""  ;
       Note = TextServer.getText().trim();
        dataOutputStream.writeUTF(Note); 
    }catch(Exception e){}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try{
              
            String Note = ""  ;
            Note = TextServer.getText().trim();
            dataOutputStream.writeUTF(Note); 
        }catch(Exception e){}
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
        
        
            
         String msgin ="";
         boolean HillorDES ;
        try{
        
              serversocket = new ServerSocket(1201); //server start at 1201 port number
              socket=serversocket.accept(); // now server will accepts the concections 
             dataInputStream = new DataInputStream(socket.getInputStream());
             dataOutputStream = new DataOutputStream(socket.getOutputStream());
        
              // msgin =   din.readUTF() ;
           while(!msgin.equals("exit") ){
              HillorDES=  dataInputStream.readBoolean() ;
              if(HillorDES==false){
                  
             
              msgin =   decrypt( dataInputStream.readUTF())  ;
  
             TextAreaServer.setText(TextAreaServer.getText().trim() + " \n Me : "+ msgin); //display message form client to server 
              }
              
              else{
                  
             //   msgin= EncrytionOrDecryption(true, din.readUTF() );
             
           msgin =   dataInputStream.readUTF() ;
    
             TextAreaServer.setText(TextAreaServer.getText().trim() + " \n Me : "+ msgin); //display message form client to server 
              }
              
           }
          
          
         
             
             
        }catch(Exception e){}
    }
    
  
    
    
    private static final byte[] Initial_Permutation = { 
		58, 50, 42, 34, 26, 18, 10, 2,
		60, 52, 44, 36, 28, 20, 12, 4,
		62, 54, 46, 38, 30, 22, 14, 6,
		64, 56, 48, 40, 32, 24, 16, 8,
		57, 49, 41, 33, 25, 17, 9,  1,
		59, 51, 43, 35, 27, 19, 11, 3,
		61, 53, 45, 37, 29, 21, 13, 5,
		63, 55, 47, 39, 31, 23, 15, 7
	};
	
    

	private static final byte[] Permuted_Choice_1 = {
		57, 49, 41, 33, 25, 17, 9,
		1,  58, 50, 42, 34, 26, 18,
		10, 2,  59, 51, 43, 35, 27,
		19, 11, 3,  60, 52, 44, 36,
		63, 55, 47, 39, 31, 23, 15,
		7,  62, 54, 46, 38, 30, 22,
		14, 6,  61, 53, 45, 37, 29,
		21, 13, 5,  28, 20, 12, 4
	};
    
    

	private static final byte[] Permuted_Choice_2 = {
		14, 17, 11, 24, 1,  5,
		3,  28, 15, 6,  21, 10,
		23, 19, 12, 4,  26, 8,
		16, 7,  27, 20, 13, 2,
		41, 52, 31, 37, 47, 55,
		30, 40, 51, 45, 33, 48,
		44, 49, 39, 56, 34, 53,
		46, 42, 50, 36, 29, 32
	};
        
        
    
	private static final byte[] rotations = {
		1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
	};
        
        
        
 
	private static final byte[] Expansion = {
		32, 1,  2,  3,  4,  5,
		4,  5,  6,  7,  8,  9,
		8,  9,  10, 11, 12, 13,
		12, 13, 14, 15, 16, 17,
		16, 17, 18, 19, 20, 21,
		20, 21, 22, 23, 24, 25,
		24, 25, 26, 27, 28, 29,
		28, 29, 30, 31, 32, 1
	};
        
        
        
        
 
	private static final byte[][] S_boxes = { {
		14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
		0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
		4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
		15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
	}, {
		15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
		3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
		0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
		13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
	}, {
		10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
		13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
		13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
		1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
	}, {
		7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
		13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
		10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
		3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
	}, {
		2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
		14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
		4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
		11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
	}, {
		12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
		10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
		9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
		4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
	}, {
		4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
		13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
		1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
		6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
	}, {
		13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
		1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
		7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
		2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
	} };
	
        
        

	private static final byte[] Permutation = {
		16, 7,  20, 21,
		29, 12, 28, 17,
		1,  15, 23, 26,
		5,  18, 31, 10,
		2,  8,  24, 14,
		32, 27, 3,  9,
		19, 13, 30, 6,
		22, 11, 4,  25
	};
	
        

	private static final byte[] Final_permutation = {
		40, 8, 48, 16, 56, 24, 64, 32,
		39, 7, 47, 15, 55, 23, 63, 31,
		38, 6, 46, 14, 54, 22, 62, 30,
		37, 5, 45, 13, 53, 21, 61, 29,
		36, 4, 44, 12, 52, 20, 60, 28,
		35, 3, 43, 11, 51, 19, 59, 27,
		34, 2, 42, 10, 50, 18, 58, 26,
		33, 1, 41, 9, 49, 17, 57, 25
	};
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea TextAreaServer;
    private javax.swing.JTextField TextServer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
