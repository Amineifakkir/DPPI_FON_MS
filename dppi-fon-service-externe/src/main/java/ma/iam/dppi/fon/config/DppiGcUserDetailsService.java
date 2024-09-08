package ma.iam.dppi.fon.config;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Z.BELGHAOUTI
 */
@Component
public class DppiGcUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findUserExterneByLogin(username);
        if (user == null)
            throw new UsernameNotFoundException("Impossible to find a user for the given username");

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        for (Profil p : user.getListProfils()) {
        	authorities.add(new SimpleGrantedAuthority(p.getLibelle()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }
}