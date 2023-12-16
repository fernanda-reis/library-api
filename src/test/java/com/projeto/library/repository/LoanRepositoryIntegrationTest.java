package com.projeto.library.repository;

import com.projeto.library.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoanRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LoanRepository loanRepository;

    @BeforeAll
    public void setUp(){
        User user = new User(1, "João", "joao@email.com", "123456");
        userRepository.save(user);

        Author author = new Author(1, "Haruki Murakami", "Japan");
        authorRepository.save(author);

        Category category = new Category(1, "Fiction");
        categoryRepository.save(category);

        Book book = new Book(1, "1Q84", 2009, author, category);
        bookRepository.save(book);

        Loan loan = new Loan(1, user, List.of(book));
        loanRepository.save(loan);
    }

    @Test
    public void LoanRepository_findAllByUser_ReturnListOfLoansByUserId(){
        Integer userId = 1;

        List<Loan> loans = loanRepository.findAllByUser(userId);
        Assertions.assertTrue(loans.size() == 1);
        Assertions.assertEquals(loans.get(0).getUser().getId(), userId);

    }

    @Test
    public void LoanRepository_findAllByBook_ReturnListOfLoansByBookId(){
        Integer bookId = 1;

        List<Loan> loans = loanRepository.findAllByBook(bookId);
        Assertions.assertTrue(loans.size() == 1);
        Assertions.assertEquals(loans.get(0).getBooks().get(0).getId(), bookId);
    }
}