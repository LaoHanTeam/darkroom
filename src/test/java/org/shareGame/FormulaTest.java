package org.shareGame;

import org.shareGame.depository.FormulaLibrary;

/**
 * 
 * @author baihui.lbh
 * @version $Id: FormulaTest.java, v 0.1 2014年8月18日 下午8:42:24 baihui.lbh Exp $
 */
public class FormulaTest {
    public static void main(String[] args) {
        FormulaLibrary instance = FormulaLibrary.getInstance();
        System.out.println(instance);
    }
}
