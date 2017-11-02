package servlets;

import businesslogic.NoteService;
import domainmodel.Note;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NoteService service = new NoteService();
        String action = request.getParameter("action");

        if (action != null && action.equals("view")) {

            int selectedNoteId = Integer.parseInt(request.getParameter("selectedNoteId"));

            try {
                Note note = service.get(selectedNoteId);
                request.setAttribute("selectedNote", note);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        List<Note> notes = null;

        try {
            notes = (List<Note>) service.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
    
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String noteId = request.getParameter("noteId");
        String contents = request.getParameter("contents");

        int noteIdInt = -1;

        if (noteId != null) {
            noteIdInt = Integer.parseInt(noteId);
        }

        NoteService service = new NoteService();

        try {

            if (action.equals("delete")) {

                int selectedNoteId = Integer.parseInt(request.getParameter("selectedNoteId"));
                service.delete(selectedNoteId);
            } else if (action.equals("edit") && !contents.equals("") && contents != null) {
                service.update(noteIdInt, contents);
            } else if (action.equals("add") && !contents.equals("") && contents != null) {
                service.insert(contents);
            }
            else request.setAttribute("errorMessage", "Please enter something into the comment field.");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        List<Note> notes = null;

        try {
            notes = (List<Note>) service.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
}
