package views;

import models.Project;
import play.api.mvc.RequestHeader;
import views.html.*;
import static org.junit.Assert.*;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.junit.Test;

import play.api.http.MediaRange;
import play.i18n.Lang;
import play.libs.typedmap.TypedKey;
import play.libs.typedmap.TypedMap;
import play.mvc.Http;
import play.mvc.Http.Cookie;
import play.mvc.Http.Cookies;
import play.mvc.Http.Headers;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBody;
import play.twirl.api.Content;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.GET;

public class ViewsTest {
    @Test
    public void testIndexView() {
        List<Project> repoList = new ArrayList<Project>();
        Http.Request request = new Request() {
            @Override
            public RequestBody body() {
                return null;
            }

            @Override
            public Request withBody(RequestBody body) {
                return null;
            }

            @Override
            public String host() {
                return null;
            }

            @Override
            public String path() {
                return null;
            }

            @Override
            public List<Lang> acceptLanguages() {
                return null;
            }

            @Override
            public List<MediaRange> acceptedTypes() {
                return null;
            }

            @Override
            public boolean accepts(String mimeType) {
                return false;
            }

            @Override
            public Map<String, String[]> queryString() {
                return null;
            }

            @Override
            public String getQueryString(String key) {
                return null;
            }

            @Override
            public Cookies cookies() {
                return null;
            }

            @Override
            public Cookie cookie(String name) {
                return null;
            }

            @Override
            public Headers getHeaders() {
                return null;
            }

            @Override
            public boolean hasBody() {
                return false;
            }

            @Override
            public Optional<String> contentType() {
                return Optional.empty();
            }

            @Override
            public Optional<String> charset() {
                return Optional.empty();
            }

            @Override
            public Optional<List<X509Certificate>> clientCertificateChain() {
                return Optional.empty();
            }

            @Override
            public Map<String, String> tags() {
                return null;
            }

            @Override
            public RequestHeader _underlyingHeader() {
                return null;
            }

            @Override
            public String uri() {
                return null;
            }

            @Override
            public String method() {
                return null;
            }

            @Override
            public String version() {
                return null;
            }

            @Override
            public String remoteAddress() {
                return null;
            }

            @Override
            public boolean secure() {
                return false;
            }

            @Override
            public TypedMap attrs() {
                return null;
            }

            @Override
            public Request withAttrs(TypedMap newAttrs) {
                return null;
            }

            @Override
            public <A> Request addAttr(TypedKey<A> key, A value) {
                return null;
            }

            @Override
            public String username() {
                return null;
            }

            @Override
            public Request withUsername(String username) {
                return null;
            }

            @Override
            public play.api.mvc.Request<RequestBody> _underlyingRequest() {
                return null;
            }

            @Override
            public play.api.mvc.Request<RequestBody> asScala() {
                return null;
            }
        };
        Content html=views.html.index.render(request);
        assertEquals("text/html",html.contentType().toString());
        assertTrue(contentAsString(html).contains("Hello"));


        html=views.html.globalstats.render(request);
        assertEquals("text/html",html.contentType().toString());


        html=views.html.owner.render(request);
        assertEquals("text/html",html.contentType().toString());


        html=views.html.projectIDStats.render(request);
        assertEquals("text/html",html.contentType().toString());

        html=views.html.skill.render(request);
        assertEquals("text/html",html.contentType().toString());

    }
}
