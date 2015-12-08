package be.looorent.petstore;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
public class IdentifiableUtilTest {

    @Test
    public void twoNullObjectsWithNullIdsReturnsReflectionEquality() {
        IdentifiableStub a = new IdentifiableStub(null);
        a.setFirstField("first");
        a.setSecondField("second");
        IdentifiableStub b = new IdentifiableStub(null);
        b.setFirstField("first");
        b.setSecondField("second");
        IdentifiableStub c = new IdentifiableStub(null);
        c.setFirstField("a");
        c.setSecondField("second");
        assertThat(IdentifiableUtil.equalsOnId(a, b), is(true));
        assertThat(IdentifiableUtil.equalsOnId(b, a), is(true));
        assertThat(IdentifiableUtil.equalsOnId(a, a), is(true));
        assertThat(IdentifiableUtil.equalsOnId(b, b), is(true));
        assertThat(IdentifiableUtil.equalsOnId(c, c), is(true));
        assertThat(IdentifiableUtil.equalsOnId(c, a), is(false));
        assertThat(IdentifiableUtil.equalsOnId(c, b), is(false));
    }

    @Test
    public void equalsOnIdHappyPath() {
        IdentifiableStub a = new IdentifiableStub(5L);
        IdentifiableStub b = new IdentifiableStub(42L);
        IdentifiableStub c = new IdentifiableStub(42L);

        assertThat(IdentifiableUtil.equalsOnId(a, b), is(false));
        assertThat(IdentifiableUtil.equalsOnId(b, a), is(false));
        assertThat(IdentifiableUtil.equalsOnId(a, a), is(true));
        assertThat(IdentifiableUtil.equalsOnId(b, b), is(true));
        assertThat(IdentifiableUtil.equalsOnId(b, c), is(true));
        assertThat(IdentifiableUtil.equalsOnId(c, b), is(true));
        assertThat(IdentifiableUtil.equalsOnId(a, c), is(false));
        assertThat(IdentifiableUtil.equalsOnId(c, a), is(false));
    }

    private static class IdentifiableStub implements Identifiable<Long> {

        private Long id;
        private String firstField;
        private String secondField;

        private IdentifiableStub(Long id) {
            this.id = id;
        }

        @Override
        public Long getId() {
            return id;
        }

        public String getSecondField() {
            return secondField;
        }

        public void setSecondField(String secondField) {
            this.secondField = secondField;
        }

        public String getFirstField() {
            return firstField;
        }

        public void setFirstField(String firstField) {
            this.firstField = firstField;
        }
    }

}
