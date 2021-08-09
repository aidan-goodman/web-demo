package com.aidan.web;

import com.aidan.pojo.Book;
import com.aidan.pojo.Page;
import com.aidan.service.BookService;
import com.aidan.service.impl.BookServiceImpl;
import com.aidan.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //3 保存Page对象到Request域中
        req.setAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        int pageTotalCount = WebUtils.parseInt(req.getParameter("pageTotalCount"), 0);

        // 如果是当前页对最后一个值，切换到下一页
        if (pageTotalCount % Page.PAGE_SIZE == 0 && pageTotalCount != 0) {
            pageNo += 1;
        }
        //        1、获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 2、调用BookService.addBook()保存图书
        bookService.addBook(book);
        // 3、跳到图书列表页面
        // /manager/bookServlet?action=list

        // 请求转发会在每次刷新的时候重新提交一次 url 带有的参数，也就是刷新一次，添加一次
        // req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数id，图书编程
        int id = WebUtils.parseInt(req.getParameter("id"), 1);
        // 2、调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
        // 3、重定向回图书列表管理页面
        // /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="
                + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2 调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3 保存到图书到Request域中
        req.setAttribute("book", book);
        //4 请求转发到 pages/manager/book_edit.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 2、调用BookService.updateBook( book );修改图书
        bookService.updateBook(book);
        // 3、重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="
                + req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();

        //2 把全部图书保存到Request域中
        req.setAttribute("books", books);

        //3、请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

}
