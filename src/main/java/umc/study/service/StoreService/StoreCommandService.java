package umc.study.service.StoreService;


import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store createStore(StoreRequestDTO.CreateStoreDTO request);
}
