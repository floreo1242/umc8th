package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateStoreDTO request) {
        Region region = regionRepository.findByName(request.getRegion())
                .orElseGet(() -> regionRepository.save(Region.builder().name(request.getRegion()).build()));
        Store newStore = StoreConverter.toStore(request, region);
        region.getStoreList().add(newStore);
        return storeRepository.save(newStore);
    }
}
