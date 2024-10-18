//package com.administrator.filmarte.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.administrator.filmarte.model.ShareSocialNetwork;
//import com.administrator.filmarte.service.ShareSocialNetworkService;
//
//@RestController
//@RequestMapping("shareSocialNetworks")
//public class ShareSocialNetworkController {
//
//    @Autowired
//    private ShareSocialNetworkService service;
//
//    @GetMapping()
//    public Iterable<ShareSocialNetwork> getAll() {
//        return service.getAll();
//    }
//
//    @GetMapping("{nameNetwork}")
//    public Iterable<ShareSocialNetwork> searchByNameNetwork(@PathVariable String nameNetwork) {
//        return service.searchByNameNetwork(nameNetwork);
//    }
//
//    @PostMapping()
//    public ResponseEntity<?> add(@RequestBody ShareSocialNetwork resource) {
//        service.add(resource);
//        return new ResponseEntity<>("Saved record", HttpStatus.OK);
//    }
//
//    @PutMapping("{idNetwork}")
//    public ResponseEntity<?> update(@RequestBody ShareSocialNetwork resource, @PathVariable String idNetwork) {
//        service.update(resource, idNetwork);
//        return new ResponseEntity<>("Updated record", HttpStatus.OK);
//    }
//
//    @DeleteMapping("{idNetwork}")
//    public ResponseEntity<?> delete(@PathVariable String idNetwork) {
//        service.delete(idNetwork);
//        return new ResponseEntity<>("Deleted record", HttpStatus.OK);
//    }
//}
