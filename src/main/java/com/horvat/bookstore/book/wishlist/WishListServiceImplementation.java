package com.horvat.bookstore.book.wishlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;
import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.exceptions.BookNotFoundException;
import com.horvat.bookstore.book.wishlist.dtos.responses.ResWishListDto;
import com.horvat.bookstore.book.wishlist.exception.ItemAlreadyInTheWishList;

@Service
public class WishListServiceImplementation implements WishListService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public List<ResBookDto> getUserWishListBooks(Integer id) {
        return ResBookDto.fromIterableEntity(this.getUserWishListBookModel(id));
    }

    @Override
    public ResWishListDto addBookToUserWishList(Integer id, Integer bookId) {
        return ResWishListDto.fromEntity(this.addBook(id, bookId));
    }

    @Override
    public ResWishListDto removeBookFromUserWishList(Integer id, Integer bookId) {
        return ResWishListDto.fromEntity(this.removeBook(id, bookId));
    }

    @Override
    public Boolean clearUserWishList(Integer id) {
        WishListModel wishList = getWishList(id);

        if(wishList.getBooks().isEmpty()){
            return true;
        }

        wishList.getBooks().clear();
        this.wishListRepository.save(wishList);

        return true;
    }


    private List<BookModel> getUserWishListBookModel(Integer id) {
        WishListModel wishList = this.getWishList(id);

        Set<BookModel> books = wishList.getBooks();
        // Books might containe hidden duplicates so we do this to remove 
        // them in the new set then we can safely convert the set to List
        Set<BookModel> uniqueBooks = new HashSet<>(books);

        return new ArrayList<>(uniqueBooks);
    }

    private WishListModel getWishList(Integer id){
        WishListModel wishList = this.getOrCreate(id);

        if(wishList.getBooks()== null){
            wishList.setBooks(new HashSet<>());
        }
        return wishList;
    }

    private WishListModel create(Integer id){
        Optional<UserModel> userOptional = this.userRepository.findById(id);

        if(!userOptional.isPresent()){
            return null;
        }

        WishListModel wishList = new WishListModel();
        UserModel user = userOptional.get();
        wishList.setUser(user);
        user.setWishlist(wishList);
        
        wishList = wishListRepository.save(wishList);
    
        return wishList;
    }

    private WishListModel getOrCreate(Integer id){
        Optional<UserModel> userOptional = this.userRepository.findById(id);

        if(!userOptional.isPresent()){
            return null;
        }
        
        UserModel user = userOptional.get();
        WishListModel wishList = user.getWishlist();
        

        if(wishList == null){
            wishList = this.create(id);
        }

        return wishList;
    }

    private boolean isBookInWishList(Integer id, Integer bookId){
        List<BookModel> wishListBooks = this.getUserWishListBookModel(id);

        return wishListBooks.stream().map(BookModel::getId).anyMatch(dtoId -> dtoId == bookId);
    }

    private WishListModel addBook(Integer id, Integer bookId){
        if(this.isBookInWishList(id, bookId)){
            throw new ItemAlreadyInTheWishList("Item already in the wishlist");
        }
        Optional<BookModel> bookOptional = this.bookRepository.findById(bookId);
        if(!bookOptional.isPresent()){
            StringBuilder sb = new StringBuilder();
            sb.append("Book with Id: ").append(bookId.toString()).append(" NotFound");
            throw new BookNotFoundException(sb.toString());
        }
        
        WishListModel wishList = this.getWishList(id);
        wishList.getBooks().add(bookOptional.get());
        return this.wishListRepository.save(wishList);
    }

    private WishListModel removeBook(Integer id, Integer bookId){
        WishListModel wishList = this.getWishList(id);
        if(this.isBookInWishList(id, bookId)){
            Optional<BookModel> bookOptional = this.bookRepository.findById(bookId);
            if(!bookOptional.isPresent()){
                StringBuilder sb = new StringBuilder();
                sb.append("Book with Id: ").append(bookId.toString()).append(" NotFound");
                throw new BookNotFoundException(sb.toString());
            }

            wishList.getBooks().remove(bookOptional.get());
            wishList = this.wishListRepository.save(wishList);
        }


        return wishList;
    }

}
