/*
 * package org.example.PrimeraEntregaWeb.exceptions;
 * 
 * import java.util.NoSuchElementException;
 * 
 * import org.springframework.ui.Model;
 * import org.springframework.web.bind.MissingServletRequestParameterException;
 * import org.springframework.web.bind.annotation.ControllerAdvice;
 * import org.springframework.web.bind.annotation.ExceptionHandler;
 * import org.springframework.web.servlet.ModelAndView;
 * 
 * @ControllerAdvice // AOP
 * public class MyCustomErrorController {
 * 
 * @ExceptionHandler(NoSuchElementException.class)
 * public ModelAndView handleNotFound(Model model, NoSuchElementException
 * exception) {
 * model.addAttribute("exceptionText", exception.toString());
 * return new ModelAndView("pagina-error");
 * }
 * 
 * @ExceptionHandler(MissingServletRequestParameterException.class)
 * public ModelAndView handleErrorNoParam(Model model,
 * MissingServletRequestParameterException exception) {
 * model.addAttribute("exceptionText", exception.toString());
 * return new ModelAndView("pagina-error");
 * }
 * 
 * }
 */