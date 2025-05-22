import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

/**
 * Controller class for managing and displaying a matrix on a Canvas using JavaFX.
 * The matrix is displayed as a grid of cells, where some cells are randomly filled.
 */
public class MatrixController {
    private static final int CELL_SIZE = 20; 
    private static final int GRID_SIZE = 20; 
    private static final double FILL_PROBABILITY = 0.1; 

    @FXML
    private Canvas canvas; 
    
    
    @FXML
    // Handles the refresh button click event. Triggers redrawing of the matrix.
    private void handleRefresh() {
        drawMatrix();
    }

    /**
     * Draws the matrix on the canvas.
     * Clears the previous content and fills cells randomly based on the fill probability.
     */
    private void drawMatrix() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Random random = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (random.nextDouble() < FILL_PROBABILITY) {
                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    gc.strokeRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
}
