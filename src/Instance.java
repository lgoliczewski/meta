import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class Instance {

    private String name;

    public enum type_enum {
        TSP, ATSP
    }
    private type_enum type;

    //private String comment;
    private int dimension;

    public enum edge_weight_type_enum {
        EXPLICIT, EUC_2D
    }
    private edge_weight_type_enum edge_weight_type;

    public enum edge_weight_format_enum {
        FULL_MATRIX, LOWER_DIAG_ROW
    }
    private edge_weight_format_enum edge_weight_format;

    /*private enum edge_data_format_enum {
        EDGE_LIST, ADJ_LIST;
    }
    private edge_data_format_enum edge_data_format;

    private enum node_coord_type_enum {
        TWOD_COORDS, THREED_COORDS, NO_COORDS;
    }
    private node_coord_type_enum node_coord_type;
    */
    public enum display_data_type_enum {
        COORD_DISPLAY, TWO_DISPLAY, NO_DISPLAY
    }
    private display_data_type_enum display_data_type;

    public List<Point2D.Double> node_coord_list;
    public int[][] edge_weight_matrix;

    public String getName() {
        return name;
    }

    /*public String getComment() {
        return comment;
    }*/

    public type_enum getType() {
        return type;
    }

    public int getDimension() {
        return dimension;
    }

    public edge_weight_type_enum getEdge_weight_type() {
        return edge_weight_type;
    }

    public edge_weight_format_enum getEdge_weight_format() {
        return edge_weight_format;
    }

    /*public edge_data_format_enum getEdge_data_format() {
        return edge_data_format;
    }

    public node_coord_type_enum getNode_coord_type() {
        return node_coord_type;
    }*/

    public display_data_type_enum getDisplay_data_type() {
        return display_data_type;
    }

    public void setType(type_enum type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setEdge_weight_type(edge_weight_type_enum edge_weight_type) {
        this.edge_weight_type = edge_weight_type;
    }

    public void setEdge_weight_format(edge_weight_format_enum edge_weight_format) {
        this.edge_weight_format = edge_weight_format;
    }

    public void setDisplay_data_type(display_data_type_enum display_data_type) {
        this.display_data_type = display_data_type;
    }


    public Instance() {

    }

}
