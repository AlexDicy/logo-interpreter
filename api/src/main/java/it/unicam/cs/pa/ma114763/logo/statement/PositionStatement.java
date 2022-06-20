package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

/**
 * A statement that changes in any way the position/direction of the cursor.
 *
 * @author Lorenzo Lapucci
 */
public sealed interface PositionStatement extends Statement permits HomeStatement, MoveStatement, RotateAngleStatement {
}
