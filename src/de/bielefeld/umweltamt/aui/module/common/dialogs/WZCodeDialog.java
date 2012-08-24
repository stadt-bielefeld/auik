package de.bielefeld.umweltamt.aui.module.common.dialogs;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.tipi.AuikWzCode;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog;

/**
 * Select a WZ-Code from the complete list of WZ-Codes
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class WZCodeDialog extends SimpleDialog {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private AuikWzCode selectedWZCode = null;
    private JTree wzCodeTree;
    private BetreiberEditor.OKListener okListener;

    public WZCodeDialog(String title, HauptFrame frame) {
        super(title, frame);
    }

    @Override
    protected JComponent buildContentArea() {
        List<AuikWzCode> listWZCodes = DatabaseQuery.getAllSortedAuikWzCodes();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("WZ-Code:");
        DefaultMutableTreeNode currentRootNode = root;

        for (AuikWzCode wzCode : listWZCodes) {
            currentRootNode = root;
            log.debug(wzCode.getEbene() + ":" + wzCode);
            switch (wzCode.getEbene()) {
                case 6:
                    currentRootNode = (DefaultMutableTreeNode)
                        currentRootNode.getChildAt(
                            currentRootNode.getChildCount()-1);
                case 5:
                    currentRootNode = (DefaultMutableTreeNode)
                        currentRootNode.getChildAt(
                            currentRootNode.getChildCount()-1);
                case 4:
                    currentRootNode = (DefaultMutableTreeNode)
                        currentRootNode.getChildAt(
                            currentRootNode.getChildCount()-1);
                case 3:
                    currentRootNode = (DefaultMutableTreeNode)
                        currentRootNode.getChildAt(
                            currentRootNode.getChildCount()-1);
                case 2:
                    currentRootNode = (DefaultMutableTreeNode)
                        currentRootNode.getChildAt(
                            currentRootNode.getChildCount()-1);
                case 1:
                    currentRootNode.add(new DefaultMutableTreeNode(
                        wzCode.getBezeichnung()));
            }
        }

        wzCodeTree = new JTree(root);

        // Expand all nodes
        for (int i = 0; i < wzCodeTree.getRowCount(); i++) {
            wzCodeTree.expandRow(i);
        }

        return new JScrollPane(wzCodeTree);
    }

    @Override
    protected Action getFirstButtonAction() {
        return new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wzCodeTree.isSelectionEmpty()) {
                    dispose();
                    return;
                }
                selectedWZCode = AuikWzCode.findById(
                    (String) ((DefaultMutableTreeNode)
                    wzCodeTree.getSelectionPath().getLastPathComponent())
                    .getUserObject());
                log.debug(selectedWZCode);
                fireOKEvent(selectedWZCode);
                dispose();
            }
        };
    }

    public void addOKListener(BetreiberEditor.OKListener listener) {
        this.okListener = listener;
    }

    protected void fireOKEvent(AuikWzCode selectedWZCode) {
        if (this.okListener == null) {
            return;
        }
        this.okListener.onOK(selectedWZCode);
    }
}
