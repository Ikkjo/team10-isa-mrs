package team10.app.repository;

import org.springframework.data.domain.Page;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.Address;
import team10.app.model.BusinessClient;
import team10.app.model.Picture;
import team10.app.model.RentalEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RentalEntityRepository extends JpaRepository<RentalEntity, UUID>,
                                                JpaSpecificationExecutor<RentalEntity> {

    @Query("select rE from RentalEntity rE where rE.owner = ?1 and rE.deleted = false")
    List<RentalEntity> findAllByOwner(BusinessClient businessClient);

    @Query("select rE.title from RentalEntity rE where rE.owner = ?1")
    List<String> getAllTitlesByOwner(BusinessClient businessClient);

    @Modifying
    @Query("update RentalEntity rE set rE.title = ?1 where rE.id = ?2")
    void updateTitle(String title, UUID id);

    @Modifying
    @Query("update RentalEntity rE set rE.address = ?1 where rE.id = ?2")
    void updateAddress(Address address, UUID id);

    @Modifying
    @Query("update RentalEntity rE set rE.description = ?1 where rE.id = ?2")
    void updateDescription(String description, UUID id);

    @Modifying
    @Query("update RentalEntity rE set rE.rulesOfConduct = ?1 where rE.id = ?2")
    void updateRulesOfConduct(String rulesOfConduct, UUID id);

    @Modifying
    @Query("update RentalEntity rE set rE.additionalServices = ?1 where rE.id = ?2")
    void updateAdditionalServices(String additionalServices, UUID id);

    @Modifying
    @Query("update RentalEntity rE set rE.price = ?1 where rE.id = ?2")
    void updatePrice(int price, UUID id);

    @Modifying
    @Query("update RentalEntity rE set rE.deleted = ?1 where rE.id = ?2")
    void updateDeleted(boolean deleted, UUID id);
                                                  
    @Query(
            value = "SELECT re FROM RentalEntity re LEFT JOIN Address addr ON addr.id=re.address " +
                    "WHERE re.title LIKE ?1 AND" +
                    " re.description LIKE ?1 AND addr.country LIKE ?2 AND addr.city LIKE ?3 AND" +
                    " addr.address LIKE ?4 AND re.price BETWEEN ?5 AND ?6",
            countQuery = "SELECT count(re) FROM RentalEntity re LEFT JOIN Address addr ON addr.id=re.address " +
                    "WHERE re.title LIKE ?1 AND" +
                    " re.description LIKE ?1 AND addr.country LIKE ?2 AND addr.city LIKE ?3 AND" +
                    " addr.address LIKE ?4 AND re.price BETWEEN ?5 AND ?6"
    )
    Page<RentalEntity> searchRentalEntities(String title, String country,
                                            String city, String address, int minPrice, int maxPrice, Pageable pageable);

    @Query("select rE.title from RentalEntity rE where rE.id = ?1")
    String getTitleById(UUID id);

}
