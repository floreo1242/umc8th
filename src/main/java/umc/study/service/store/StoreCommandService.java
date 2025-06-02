package umc.study.service.store;


import umc.study.domain.Store;
import umc.study.web.dto.store.StoreRequestDTO;

public interface StoreCommandService {

    Store createStore(StoreRequestDTO.CreateStoreDTO request);
}
