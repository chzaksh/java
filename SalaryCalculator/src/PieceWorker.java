import java.util.Calendar;

//PieceWorker.java - Employee paid by number of pieces produced
public class PieceWorker extends Employee {
    private int piecesProduced;
    private double wagePerPiece;

    public PieceWorker(String firstName, String lastName, String socialSecurityNumber,
                       Calendar birthDate, int piecesProduced, double wagePerPiece) {
        super(firstName, lastName, socialSecurityNumber, birthDate);
        setPiecesProduced(piecesProduced);
        setWagePerPiece(wagePerPiece);
    }

    public void setPiecesProduced(int piecesProduced) {
        if (piecesProduced < 0) {
            throw new IllegalArgumentException("Number of pieces must be >= 0.");
        }
        this.piecesProduced = piecesProduced;
    }

    public void setWagePerPiece(double wagePerPiece) {
        if (wagePerPiece < 0.0) {
            throw new IllegalArgumentException("Wage per piece must be >= 0.0.");
        }
        this.wagePerPiece = wagePerPiece;
    }

    public int getPiecesProduced() {
        return piecesProduced;
    }

    public double getWagePerPiece() {
        return wagePerPiece;
    }   

    @Override
    public double earnings() {
        return applyBirthdayBonus(piecesProduced * wagePerPiece);
    }

    @Override
    public String toString() {
        return String.format("Piece Worker: %s\nPieces Produced: %d; Wage Per Piece: $%,.2f",
                super.toString(), getPiecesProduced(), getWagePerPiece());
    }
}
