package org.Sikoling.ejb.main.security;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTProcessor;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;

@Stateless
@LocalBean
public class SecurityProvider {
	
//	@Produces
//	public KeycloakClient getKeycloakClient(Properties properties) {
//		return new KeycloakClient(
//				properties.getProperty("SSO_AUTH_URL"), properties.getProperty("SSO_REALM"), 
//				properties.getProperty("SSO_CLIENT_ID"), properties.getProperty("SSO_CLIENT_SECRET")
//				);
//	}

	@Produces
	public JWTProcessor<SecurityContext> getJWTProcessor(Properties properties) throws MalformedURLException {		
		ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<> ();
		JWKSource<SecurityContext> keySource = new RemoteJWKSet<> (new URL(properties.getProperty("SSO_JWK_URL")));
		JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;
		JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<> (expectedJWSAlg, keySource);
        jwtProcessor.setJWSKeySelector(keySelector);

        return jwtProcessor;		
	}
	
//	@Produces
//    public ITokenValidationService getTokenValidationService(JWTProcessor<SecurityContext> jwtProcessor) {
//        return new TokenValidationJWK(jwtProcessor);
//    }
}
